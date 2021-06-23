# 크롤링한 스팀 상점 html에서 데이터 추출
import csv

input_file_name = "./data/temp.txt"
output_file_name = "./data/GamedataRow.csv"
appid = 0
name = ''
imgsrc = ''
released_date = ''
csvData = []
linedata = {}
cnt = 0
dic = {}
tag = ""
score = ""
votes = ""
tags = [{"tagid": 492, "name": "Indie"}, {"tagid": 19, "name": "Action"}, {"tagid": 21, "name": "Adventure"},
        {"tagid": 597, "name": "Casual"}, {"tagid": 599, "name": "Simulation"}, {"tagid": 9, "name": "Strategy"},
        {"tagid": 122, "name": "RPG"}, {"tagid": 493, "name": "Early Access"}, {"tagid": 4182, "name": "Singleplayer"},
        {"tagid": 113, "name": "Free to Play"}, {"tagid": 3871, "name": "2D"}, {"tagid": 4667, "name": "Violent"},
        {"tagid": 701, "name": "Sports"}, {"tagid": 128, "name": "Massively Multiplayer"},
        {"tagid": 4166, "name": "Atmospheric"}, {"tagid": 3859, "name": "Multiplayer"},
        {"tagid": 1664, "name": "Puzzle"}, {"tagid": 699, "name": "Racing"}, {"tagid": 1742, "name": "Story Rich"},
        {"tagid": 4345, "name": "Gore"}, {"tagid": 1684, "name": "Fantasy"}, {"tagid": 6650, "name": "Nudity"},
        {"tagid": 12095, "name": "Sexual Content"}, {"tagid": 4085, "name": "Anime"},
        {"tagid": 3964, "name": "Pixel Graphics"}, {"tagid": 1756, "name": "Great Soundtrack"},
        {"tagid": 4191, "name": "3D"}, {"tagid": 4305, "name": "Colorful"}, {"tagid": 4136, "name": "Funny"},
        {"tagid": 3839, "name": "First-Person"}, {"tagid": 3942, "name": "Sci-fi"}, {"tagid": 4726, "name": "Cute"},
        {"tagid": 1774, "name": "Shooter"}, {"tagid": 1667, "name": "Horror"}, {"tagid": 1773, "name": "Arcade"},
        {"tagid": 3834, "name": "Exploration"}, {"tagid": 4004, "name": "Retro"}, {"tagid": 21978, "name": "VR"},
        {"tagid": 5350, "name": "Family Friendly"}, {"tagid": 4026, "name": "Difficult"},
        {"tagid": 1685, "name": "Co-op"}, {"tagid": 1625, "name": "Platformer"}, {"tagid": 1695, "name": "Open World"},
        {"tagid": 1662, "name": "Survival"}, {"tagid": 1654, "name": "Relaxing"}, {"tagid": 1719, "name": "Comedy"},
        {"tagid": 7208, "name": "Female Protagonist"}, {"tagid": 1663, "name": "FPS"},
        {"tagid": 4106, "name": "Action-Adventure"}, {"tagid": 1697, "name": "Third Person"},
        {"tagid": 3843, "name": "Online Co-Op"}, {"tagid": 3799, "name": "Visual Novel"},
        {"tagid": 1775, "name": "PvP"}, {"tagid": 3968, "name": "Physics"}, {"tagid": 4175, "name": "Realistic"},
        {"tagid": 3810, "name": "Sandbox"}, {"tagid": 4252, "name": "Stylized"},
        {"tagid": 84, "name": "Design & Illustration"}, {"tagid": 6426, "name": "Choices Matter"},
        {"tagid": 87, "name": "Utilities"}, {"tagid": 3993, "name": "Combat"}, {"tagid": 4791, "name": "Top-Down"},
        {"tagid": 4342, "name": "Dark"}, {"tagid": 5716, "name": "Mystery"},
        {"tagid": 1721, "name": "Psychological Horror"}, {"tagid": 1677, "name": "Turn-Based"},
        {"tagid": 1755, "name": "Space"}, {"tagid": 4747, "name": "Character Customization"},
        {"tagid": 1708, "name": "Tactical"}, {"tagid": 4195, "name": "Cartoony"},
        {"tagid": 7368, "name": "Local Multiplayer"}, {"tagid": 1643, "name": "Building"},
        {"tagid": 1698, "name": "Point & Click"}, {"tagid": 4711, "name": "Replay Value"},
        {"tagid": 6971, "name": "Multiple Endings"}, {"tagid": 7481, "name": "Controller"},
        {"tagid": 12472, "name": "Management"}, {"tagid": 4094, "name": "Minimalist"},
        {"tagid": 3916, "name": "Old School"}, {"tagid": 1036, "name": "Education"},
        {"tagid": 5379, "name": "2D Platformer"}, {"tagid": 4231, "name": "Action RPG"}, {"tagid": 6730, "name": "PvE"},
        {"tagid": 4295, "name": "Futuristic"}, {"tagid": 1702, "name": "Crafting"},
        {"tagid": 3798, "name": "Side Scroller"}, {"tagid": 5611, "name": "Mature"},
        {"tagid": 3978, "name": "Survival Horror"}, {"tagid": 1659, "name": "Zombies"},
        {"tagid": 4057, "name": "Magic"}, {"tagid": 3841, "name": "Local Co-Op"},
        {"tagid": 5537, "name": "Puzzle Platformer"}, {"tagid": 5125, "name": "Procedural Generation"},
        {"tagid": 6815, "name": "Hand-drawn"}, {"tagid": 1693, "name": "Classic"},
        {"tagid": 8945, "name": "Resource Management"}, {"tagid": 4255, "name": "Shoot 'Em Up"},
        {"tagid": 10397, "name": "Memes"}, {"tagid": 1678, "name": "War"}, {"tagid": 4562, "name": "Cartoon"},
        {"tagid": 7250, "name": "Linear"}, {"tagid": 1716, "name": "Roguelike"}, {"tagid": 4172, "name": "Medieval"},
        {"tagid": 4700, "name": "Movie"}, {"tagid": 1741, "name": "Turn-Based Strategy"},
        {"tagid": 1734, "name": "Fast-Paced"}, {"tagid": 1646, "name": "Hack and Slash"},
        {"tagid": 1038, "name": "Web Publishing"}, {"tagid": 4325, "name": "Turn-Based Combat"},
        {"tagid": 3987, "name": "Historical"}, {"tagid": 4604, "name": "Dark Fantasy"},
        {"tagid": 3959, "name": "Roguelite"}, {"tagid": 10695, "name": "Party-Based RPG"},
        {"tagid": 4434, "name": "JRPG"}, {"tagid": 4234, "name": "Short"}, {"tagid": 4947, "name": "Romance"},
        {"tagid": 3835, "name": "Post-apocalyptic"}, {"tagid": 9551, "name": "Dating Sim"},
        {"tagid": 5411, "name": "Beautiful"}, {"tagid": 872, "name": "Animation & Modeling"},
        {"tagid": 5900, "name": "Walking Simulator"}, {"tagid": 8013, "name": "Software"},
        {"tagid": 1687, "name": "Stealth"}, {"tagid": 7332, "name": "Base Building"},
        {"tagid": 13782, "name": "Experimental"}, {"tagid": 5984, "name": "Drama"}, {"tagid": 6129, "name": "Logic"},
        {"tagid": 5395, "name": "3D Platformer"}, {"tagid": 4486, "name": "Choose Your Own Adventure"},
        {"tagid": 1710, "name": "Surreal"}, {"tagid": 4885, "name": "Bullet Hell"},
        {"tagid": 14139, "name": "Turn-Based Tactics"}, {"tagid": 1743, "name": "Fighting"},
        {"tagid": 1720, "name": "Dungeon Crawler"}, {"tagid": 11014, "name": "Interactive Fiction"},
        {"tagid": 1676, "name": "RTS"}, {"tagid": 1738, "name": "Hidden Object"}, {"tagid": 5094, "name": "Narration"},
        {"tagid": 4168, "name": "Military"}, {"tagid": 3814, "name": "Third-Person Shooter"},
        {"tagid": 1027, "name": "Audio Production"}, {"tagid": 5154, "name": "Score Attack"},
        {"tagid": 1621, "name": "Music"}, {"tagid": 5711, "name": "Team-Based"}, {"tagid": 5577, "name": "RPGMaker"},
        {"tagid": 5851, "name": "Isometric"}, {"tagid": 4115, "name": "Cyberpunk"},
        {"tagid": 4637, "name": "Top-Down Shooter"}, {"tagid": 5752, "name": "Robots"},
        {"tagid": 5923, "name": "Dark Humor"}, {"tagid": 1644, "name": "Driving"},
        {"tagid": 4840, "name": "4 Player Local"}, {"tagid": 6691, "name": "1990's"},
        {"tagid": 31275, "name": "Text-Based"}, {"tagid": 3878, "name": "Competitive"},
        {"tagid": 9130, "name": "Hentai"}, {"tagid": 4695, "name": "Economy"}, {"tagid": 1673, "name": "Aliens"},
        {"tagid": 4975, "name": "2.5D"}, {"tagid": 784, "name": "Video Production"},
        {"tagid": 1666, "name": "Card Game"}, {"tagid": 7743, "name": "1980s"}, {"tagid": 15045, "name": "Flight"},
        {"tagid": 5608, "name": "Emotional"}, {"tagid": 4400, "name": "Abstract"}, {"tagid": 1669, "name": "Moddable"},
        {"tagid": 1759, "name": "Perma Death"}, {"tagid": 44868, "name": "LGBTQ+"},
        {"tagid": 1645, "name": "Tower Defense"}, {"tagid": 7948, "name": "Soundtrack"},
        {"tagid": 4145, "name": "Cinematic"}, {"tagid": 30358, "name": "Nature"},
        {"tagid": 8369, "name": "Investigation"}, {"tagid": 42804, "name": "Action Roguelike"},
        {"tagid": 9204, "name": "Immersive Sim"}, {"tagid": 15172, "name": "Conversation"},
        {"tagid": 1770, "name": "Board Game"}, {"tagid": 3955, "name": "Character Action Game"},
        {"tagid": 4328, "name": "City Builder"}, {"tagid": 4158, "name": "Beat 'em up"},
        {"tagid": 5613, "name": "Detective"}, {"tagid": 6276, "name": "Inventory Management"},
        {"tagid": 12057, "name": "Tutorial"}, {"tagid": 5547, "name": "Arena Shooter"},
        {"tagid": 4064, "name": "Thriller"}, {"tagid": 5186, "name": "Psychological"},
        {"tagid": 5363, "name": "Destruction"}, {"tagid": 3813, "name": "Real Time Tactics"},
        {"tagid": 6869, "name": "Nonlinear"}, {"tagid": 17305, "name": "Strategy RPG"},
        {"tagid": 9541, "name": "Demons"}, {"tagid": 1628, "name": "Metroidvania"}, {"tagid": 24904, "name": "NSFW"},
        {"tagid": 4236, "name": "Loot"}, {"tagid": 4190, "name": "Addictive"}, {"tagid": 5673, "name": "Modern"},
        {"tagid": 17389, "name": "Tabletop"}, {"tagid": 8122, "name": "Level Editor"},
        {"tagid": 1714, "name": "Psychedelic"}, {"tagid": 5030, "name": "Dystopian "},
        {"tagid": 4150, "name": "World War II"}, {"tagid": 1754, "name": "MMORPG"},
        {"tagid": 13906, "name": "Game Development"}, {"tagid": 16689, "name": "Time Management"},
        {"tagid": 4598, "name": "Alternate History"}, {"tagid": 379975, "name": "Clicker"},
        {"tagid": 7926, "name": "Artificial Intelligence"}, {"tagid": 4684, "name": "Wargame"},
        {"tagid": 10235, "name": "Life Sim"}, {"tagid": 6378, "name": "Crime"},
        {"tagid": 1445, "name": "Software Training"}, {"tagid": 21725, "name": "Tactical RPG"},
        {"tagid": 10808, "name": "Supernatural"}, {"tagid": 3854, "name": "Lore-Rich"},
        {"tagid": 19995, "name": "Dark Comedy"}, {"tagid": 1751, "name": "Comic Book"},
        {"tagid": 4036, "name": "Parkour"}, {"tagid": 3877, "name": "Precision Platformer"},
        {"tagid": 4758, "name": "Twin Stick Shooter"}, {"tagid": 5228, "name": "Blood"},
        {"tagid": 4364, "name": "Grand Strategy"}, {"tagid": 1100687, "name": "Automobile Sim"},
        {"tagid": 4736, "name": "2D Fighter"}, {"tagid": 29482, "name": "Souls-like"},
        {"tagid": 4508, "name": "Co-op Campaign"}, {"tagid": 5794, "name": "Science"},
        {"tagid": 7478, "name": "Illuminati"}, {"tagid": 8666, "name": "Runner"}, {"tagid": 4474, "name": "CRPG"},
        {"tagid": 16094, "name": "Mythology"}, {"tagid": 1100689, "name": "Open World Survival Craft"},
        {"tagid": 15277, "name": "Philosophical"}, {"tagid": 10816, "name": "Split Screen"},
        {"tagid": 16598, "name": "Space Sim"}, {"tagid": 7432, "name": "Lovecraftian"},
        {"tagid": 7569, "name": "Grid-Based Movement"}, {"tagid": 1752, "name": "Rhythm"},
        {"tagid": 4242, "name": "Episodic"}, {"tagid": 3965, "name": "Epic"}, {"tagid": 4161, "name": "Real-Time"},
        {"tagid": 4155, "name": "Class-Based"}, {"tagid": 7782, "name": "Cult Classic"},
        {"tagid": 4608, "name": "Swordplay"}, {"tagid": 176981, "name": "Battle Royale"},
        {"tagid": 11123, "name": "Mouse only"}, {"tagid": 5765, "name": "Gun Customization"},
        {"tagid": 29363, "name": "3D Vision"}, {"tagid": 1665, "name": "Match 3"},
        {"tagid": 809, "name": "Photo Editing"}, {"tagid": 5055, "name": "eSports"}, {"tagid": 4046, "name": "Dragons"},
        {"tagid": 4878, "name": "Parody "}, {"tagid": 1651, "name": "Satire"}, {"tagid": 4853, "name": "Political"},
        {"tagid": 1777, "name": "Steampunk"}, {"tagid": 5796, "name": "Bullet Time"}, {"tagid": 6052, "name": "Noir"},
        {"tagid": 4835, "name": "6DOF"}, {"tagid": 1732, "name": "Voxel"}, {"tagid": 11104, "name": "Vehicular Combat"},
        {"tagid": 13190, "name": "America"}, {"tagid": 17894, "name": "Cats"}, {"tagid": 32322, "name": "Deckbuilding"},
        {"tagid": 4202, "name": "Trading"}, {"tagid": 1718, "name": "MOBA"}, {"tagid": 5981, "name": "Mining"},
        {"tagid": 5372, "name": "Conspiracy"}, {"tagid": 4821, "name": "Mechs"}, {"tagid": 13276, "name": "Tanks"},
        {"tagid": 9564, "name": "Hunting"}, {"tagid": 5652, "name": "Collectathon"}, {"tagid": 5708, "name": "Remake"},
        {"tagid": 791774, "name": "Card Battler"}, {"tagid": 6625, "name": "Time Manipulation"},
        {"tagid": 4845, "name": "Capitalism"}, {"tagid": 3952, "name": "Gothic"}, {"tagid": 4754, "name": "Politics"},
        {"tagid": 1670, "name": "4X"}, {"tagid": 5502, "name": "Hacking"}, {"tagid": 1688, "name": "Ninja"},
        {"tagid": 220585, "name": "Colony Sim"}, {"tagid": 1681, "name": "Pirates"},
        {"tagid": 7107, "name": "Real-Time with Pause"}, {"tagid": 6506, "name": "3D Fighter"},
        {"tagid": 31579, "name": "Otome"}, {"tagid": 10679, "name": "Time Travel"},
        {"tagid": 22602, "name": "Agriculture"}, {"tagid": 1717, "name": "Hex Grid"},
        {"tagid": 5179, "name": "Cold War"}, {"tagid": 5153, "name": "Kickstarter"},
        {"tagid": 21006, "name": "Underground"}, {"tagid": 615955, "name": "Idler"},
        {"tagid": 5300, "name": "God Game"}, {"tagid": 4559, "name": "Quick-Time Events"},
        {"tagid": 198631, "name": "Mystery Dungeon"}, {"tagid": 6915, "name": "Martial Arts"},
        {"tagid": 24003, "name": "Word Game"}, {"tagid": 1671, "name": "Superhero"}, {"tagid": 1616, "name": "Trains"},
        {"tagid": 1638, "name": "Dog"}, {"tagid": 9592, "name": "Dynamic Narration"},
        {"tagid": 1649, "name": "GameMaker"}, {"tagid": 5160, "name": "Dinosaurs"}, {"tagid": 18594, "name": "FMV"},
        {"tagid": 255534, "name": "Automation"}, {"tagid": 87918, "name": "Farming Sim"},
        {"tagid": 15564, "name": "Fishing"}, {"tagid": 4376, "name": "Assassin"}, {"tagid": 9994, "name": "Experience"},
        {"tagid": 6910, "name": "Naval"}, {"tagid": 1647, "name": "Western"}, {"tagid": 4102, "name": "Combat Racing"},
        {"tagid": 4777, "name": "Spectacle fighter"}, {"tagid": 25085, "name": "Touch-Friendly"},
        {"tagid": 5432, "name": "Programming"}, {"tagid": 620519, "name": "Hero Shooter"},
        {"tagid": 9271, "name": "Trading Card Game"}, {"tagid": 5348, "name": "Mod"},
        {"tagid": 9157, "name": "Underwater"}, {"tagid": 17770, "name": "Asynchronous Multiplayer"},
        {"tagid": 1680, "name": "Heist"}, {"tagid": 3934, "name": "Immersive"}, {"tagid": 8093, "name": "Minigames"},
        {"tagid": 15339, "name": "Documentary"}, {"tagid": 13382, "name": "Archery"},
        {"tagid": 14153, "name": "Dungeons & Dragons"}, {"tagid": 13070, "name": "Solitaire"},
        {"tagid": 4018, "name": "Vampire"}, {"tagid": 353880, "name": "Looter Shooter"},
        {"tagid": 26921, "name": "Political Sim"}, {"tagid": 6310, "name": "Diplomacy"},
        {"tagid": 5230, "name": "Sequel"}, {"tagid": 7108, "name": "Party"}, {"tagid": 180368, "name": "Faith"},
        {"tagid": 5310, "name": "Games Workshop"}, {"tagid": 7702, "name": "Narrative"},
        {"tagid": 1730, "name": "Sokoban"}, {"tagid": 7178, "name": "Party Game"},
        {"tagid": 4994, "name": "Naval Combat"}, {"tagid": 9803, "name": "Snow"}, {"tagid": 13577, "name": "Sailing"},
        {"tagid": 150626, "name": "Gaming"}, {"tagid": 51306, "name": "Foreign"},
        {"tagid": 5390, "name": "Time Attack"}, {"tagid": 10383, "name": "Transportation"},
        {"tagid": 916648, "name": "Creature Collector"}, {"tagid": 8253, "name": "Music-Based Procedural Generation"},
        {"tagid": 7113, "name": "Crowdfunded"}, {"tagid": 11333, "name": "Villain Protagonist"},
        {"tagid": 7226, "name": "Football"}, {"tagid": 1679, "name": "Soccer"}, {"tagid": 5382, "name": "World War I"},
        {"tagid": 7423, "name": "Sniper"}, {"tagid": 15954, "name": "Silent Protagonist"},
        {"tagid": 56690, "name": "On-Rails Shooter"}, {"tagid": 7622, "name": "Offroad"},
        {"tagid": 6041, "name": "Horses"}, {"tagid": 1674, "name": "Typing"}, {"tagid": 6702, "name": "Mars"},
        {"tagid": 776177, "name": "360 Video"}, {"tagid": 16250, "name": "Gambling"},
        {"tagid": 4137, "name": "Transhumanism"}, {"tagid": 8075, "name": "TrackIR"}, {"tagid": 4184, "name": "Chess"},
        {"tagid": 1084988, "name": "Auto Battler"}, {"tagid": 17015, "name": "Werewolves"},
        {"tagid": 10437, "name": "Trivia"}, {"tagid": 6621, "name": "Pinball"}, {"tagid": 12190, "name": "Boxing"},
        {"tagid": 1736, "name": "LEGO"}, {"tagid": 1733, "name": "Unforgiving"}, {"tagid": 4520, "name": "Farming"},
        {"tagid": 14720, "name": "Nostalgia"}, {"tagid": 454187, "name": "Traditional Roguelike"},
        {"tagid": 3920, "name": "Cooking"}, {"tagid": 3796, "name": "Based On A Novel"},
        {"tagid": 12286, "name": "Warhammer 40K"}, {"tagid": 6948, "name": "Rome"},
        {"tagid": 198913, "name": "Motorbike"}, {"tagid": 7038, "name": "Golf"},
        {"tagid": 1091588, "name": "Roguelike Deckbuilder"}, {"tagid": 29855, "name": "Ambient"},
        {"tagid": 61357, "name": "Electronic Music"}, {"tagid": 1100688, "name": "Medical Sim"},
        {"tagid": 4291, "name": "Spaceships"}, {"tagid": 856791, "name": "Asymmetric VR"},
        {"tagid": 92092, "name": "Jet"}, {"tagid": 1100686, "name": "Outbreak Sim"}, {"tagid": 123332, "name": "Bikes"},
        {"tagid": 1746, "name": "Basketball"}, {"tagid": 19780, "name": "Submarine"},
        {"tagid": 71389, "name": "Spelling"}, {"tagid": 14906, "name": "Intentionally Awkward Controls"},
        {"tagid": 22955, "name": "Mini Golf"}, {"tagid": 922563, "name": "Roguevania"},
        {"tagid": 745697, "name": "Social Deduction"}, {"tagid": 1723, "name": "Action RTS"},
        {"tagid": 348922, "name": "Steam Machine"}, {"tagid": 17927, "name": "Pool"},
        {"tagid": 5407, "name": "Benchmark"}, {"tagid": 603297, "name": "Hardware"},
        {"tagid": 189941, "name": "Instrumental Music"}, {"tagid": 47827, "name": "Wrestling"},
        {"tagid": 5727, "name": "Baseball"}, {"tagid": 5914, "name": "Tennis"},
        {"tagid": 233824, "name": "Feature Film"}, {"tagid": 17337, "name": "Lemmings"},
        {"tagid": 1753, "name": "Skateboarding"}, {"tagid": 27758, "name": "Voice Control"},
        {"tagid": 324176, "name": "Hockey"}, {"tagid": 143739, "name": "Electronic"},
        {"tagid": 15868, "name": "Motocross"}, {"tagid": 7328, "name": "Bowling"}, {"tagid": 19568, "name": "Cycling"},
        {"tagid": 337964, "name": "Rock Music"}, {"tagid": 129761, "name": "ATV"}, {"tagid": 96359, "name": "Skating"},
        {"tagid": 8461, "name": "Well-Written"}, {"tagid": 117648, "name": "8-bit Music"},
        {"tagid": 7309, "name": "Skiing"}, {"tagid": 252854, "name": "BMX"}, {"tagid": 28444, "name": "Snowboarding"},
        {"tagid": 5941, "name": "Reboot"}]

with open(input_file_name, "r", encoding="utf-8", newline="") as input_file, \
        open(output_file_name, "w", encoding="UTF-8", newline="") as output_file:
    flag = False
    for line in input_file.readlines():
        # <a href="https://store.steampowered.com/app/: appid
        # 새로운 appid를 찾은 경우 기존에 저장한 정보를 csvData에 append
        if '<a href="https://store.steampowered.com/app/' in line:
            # 처음에 데이터를 아직 추출하지 못한 경우 및 꾸러미(bundle) 제외
            if flag and '꾸러미' not in votes:
                linedata = {}
                linedata['appid'] = appid
                linedata['name'] = name
                linedata['imgsrc'] = imgsrc
                linedata['released_data'] = released_date
                linedata['genre'] = tag
                
                # 긍정/부정 점수 확인
                if score == '압도적으로 긍정적':
                    score = 10
                elif score == '매우 긍정적':
                    score = 9
                elif score == '대체로 긍정적':
                    score = 8
                elif score == '긍정적':
                    score = 7
                elif score == '복합적':
                    score = 5
                elif score == '부정적':
                    score = 3
                elif score == '대체로 부정적':
                    score = 2
                elif score == '매우 부정적':
                    score = 1
                elif score == '압도적으로 부정적':
                    score = 0
                else: # 평가수가 매우 적어 점수가 공개되지 않은 데이터
                    score = -1

                

                if score != -1:
                    # 평가인원 정제
                    votes = votes.split('이 게임에 대한 사용자 평가 ')[1]
                    votes = votes.split('개 중 ')
                    score = int(votes[1].split('%')[0])
                    votes = votes[0]
                    votes = int(votes.replace(',', ''))
                else:
                    votes = 0
                linedata['score'] = score
                linedata['vote'] = votes
                # csv데이터에 정제한 데이터 저장
                if score != -1 and len(tag) != 0:
                    linedata['storelink'] = 'https://store.steampowered.com/app/' + str(appid)
                    csvData.append(linedata)
                    # 저장한 리스트 증가
                    cnt += 1
                
                # 데이터 초기화
                appid = 0
                name = ''
                imgsrc = ''
                released_date = ''
                tag = []
                score = ""
                votes = ""

                

            # 데이터 정제 시작
            flag = True

            # appid 추출
            data = line.split('<a href="https://store.steampowered.com/app/')
            data = data[1].split('/')
            appid = data[0]

            # tagid를 추출하여 사전에 저장한 tags와 비교하여 id -> name으로 변경
            data = line.split('data-ds-tagids="[')
            if len(data) > 1:
                data = data[1].split(']')
                data = data[0].split(',')
                idx = 0
                for index, value in enumerate(data):
                    for taginfo in tags:
                        if value == str(taginfo['tagid']):
                            data[index] = taginfo['name']
                    idx = 1
                tag = data

        # 1x, ~ 2x"></div>: 게임 사진 경로
        elif '2x"></div>' in line:
            # 사진 경로 추출
            data = line.split('2x"></div>')
            data = data[0].split('1x, ')
            imgsrc = data[1]

        # <span class="title">: 게임이름
        elif '<span class="title">' in line:
            # 게임 이름 추출
            data = line.split('<span class="title">')
            data = data[1].split('<')
            name = data[0]

        # <div class="col search_released responsive_secondrow">: 출시일
        elif '<div class="col search_released responsive_secondrow">' in line:
            # 출시일 추출. 아직 출시하지 않은 게임(ex. 출시예정 2025) 및 연도만 저장된 데이터 존재
            data = line.split('<div class="col search_released responsive_secondrow">')
            data = data[1].split('<')
            released_date = data[0]

            # 중요 정보인 appid, name, imgsrc가 없는 경우 데이터 제외
            if appid == 0 or name == '' or imgsrc == '':
                continue

            # 중복 데이터 제외
            if name in dic:
                continue
            else:
                dic[name] = appid

        # data-tooltip-html=": 평가 및 평가인원
        elif ' data-tooltip-html="' in line:
            # score 데이터 및 votes데이터 추출
            data = line.split(' data-tooltip-html="')
            data = data[1].split('<br>')
            score = data[0]
            votes = data[1]


    # 마지막에 남은 않은 데이터 삽입
    linedata = {}
    linedata['appid'] = appid
    linedata['name'] = name
    linedata['imgsrc'] = imgsrc
    linedata['released_data'] = released_date
    linedata['genre'] = tag
    
    # 마지막 데이터에는 votes 및 score 데이터가 존재하지 않음
    linedata['score'] = 3
    linedata['votes'] = 0

    # csv데이터에 정제한 데이터 저장
    linedata['storelink'] = 'https://store.steampowered.com/app/' + str(appid)
    csvData.append(linedata)
    cnt += 1

    # csv파일에 값 저장
    csvwriter = csv.writer(output_file)
    csvwriter.writerow(csvData[0].keys())
    for line in csvData:
        csvwriter.writerow(line.values())

    # 저장한 게임 수 출력
    print(cnt)
