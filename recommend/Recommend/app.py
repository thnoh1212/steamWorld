from flask import Flask, Response, request
from CBF.CBF import ContentsBasedFiltering
from CF.CF import CollaborativeFiltering
from flask_cors import CORS
import werkzeug
werkzeug.cached_property = werkzeug.utils.cached_property
from flask_restplus import Api, Resource, fields
import json

app = Flask(__name__)

## CORS 에러 방지
CORS(app)

## Swagger API 설정
api = Api(app, version='1.0', title='Recommendation API',
          description='게임 추천 API',)

recomm = api.namespace('rec', description='게임추천 API 목록')
app.config.SWAGGER_UI_DOC_EXPANSION = 'list'

info = recomm.model('info', {  # Model 객체 생성
    'appids': fields.List(fields.Integer),
    'steamid' : fields.Integer,
})

@recomm.route('/wish/')
class wish(Resource):
    @recomm.expect(info)
    def post(self):
        params = json.loads(request.get_data(), encoding='utf-8')
        appids = params['appids']
        steamid = params['steamid']
        if len(appids) == 0:
            resp = {
                'success': 'fail',
                'data': '',
                'reason': '관심목록에 추가된 게임이 없습니다.'
            }
            return Response(json.dumps(resp, ensure_ascii=False), content_type='application/json; charset=utf-8')
        data = CollaborativeFiltering(steamid)
        reason = ''
        if steamid != 0:
            reason, temp = data.getUserData()

        data.addData(appids, steamid)
        data.refine()
        already_rated, predictions = data.recommend_games()
        result = predictions.to_json(orient='records')
        parsed = json.loads(result)
        resp = {
            'success': 'success',
            'data': parsed,
            'reason': reason,
        }

        return Response(json.dumps(resp, ensure_ascii=False), content_type='application/json; charset=utf-8')


@recomm.route('/cbf/<int:appid>')
class CBF(Resource):
    def get(self, appid):
        data = ContentsBasedFiltering(appid)
        data.makePoint()
        if not data.isAppidValid:
            resp = {
                'success': 'fail',
                'data': '게임 조회에 오류가 발생했습니다.'
            }
            return Response(json.dumps(resp, ensure_ascii=False), content_type='application/json; charset=utf-8')
        data.refine()
        data.simEval()
        result = data.result(10).to_json(orient='records')
        parsed = json.loads(result)
        resp = {
            'success': 'success',
            'data': parsed
        }
        return Response(json.dumps(resp, ensure_ascii=False), content_type='application/json; charset=utf-8')


@recomm.route('/cf/<int:steamid>')
class CF(Resource):
    def get(self, steamid):
        data = CollaborativeFiltering(steamid)
        reason, temp = data.getUserData()
        if reason == 'success':
            data.refine()
            already_rated, predictions = data.recommend_games()
            result = predictions.to_json(orient='records')
            parsed = json.loads(result)
            resp = {
                'success': 'success',
                'data': parsed,
                'reason': reason,
            }
            return Response(json.dumps(resp, ensure_ascii=False), content_type='application/json; charset=utf-8')
        else:
            resp = {
                'success': 'fail',
                'data': '',
                'reason': reason,
            }
            return Response(json.dumps(resp, ensure_ascii=False), content_type='application/json; charset=utf-8')


@recomm.route('/steamid/<int:steamid>')
class STEAMID(Resource):
    def get(self, steamid):
        data = CollaborativeFiltering(steamid)
        reason, data = data.getUserData()
        result = data.to_json(orient='records')
        parsed = json.loads(result)
        resp = {
            'success': reason,
            'data': parsed
        }
        return Response(json.dumps(resp, ensure_ascii=False), content_type='application/json; charset=utf-8')


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)