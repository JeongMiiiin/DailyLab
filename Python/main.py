from fastapi import FastAPI
import uvicorn
from pydantic import BaseModel
import openai
from starlette.middleware.cors import CORSMiddleware

# openai.api_key = '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'
openai.api_key = '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' # 디에고

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # React 앱의 주소에 따라 조정
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

class Request(BaseModel):
    diary: str

class Response:
    def __init__(self, request: Request):
        self.response = openai.ChatCompletion.create(
            model="gpt-4",
            messages=[
                        {"role": "system", "content": "너는 다음과 같은 특징을 가진 사람이야\n"
                                      "성격: 내향형\n"
                                      "성별: 남자\n"
                                      "나이 : 20대"
                                      "생년월일: 1995-02-06\n"
                                      "직업 : 무직, 취업준비생\n" 
                                      "목표: 취업\n"
                                      "거주지: 대전시, 원룸"
                                      "취미: 러닝, 수영\n"
                                      "좋아하는 음식: 피자\n"
                                      "좋아하는 여행지: 뉴욕\n"
                                    #   "말투: 오늘 하루는 무난하기 그지없었다. 아무런 사건도, 특별한 일도 없었다. 그저 프로젝트를 진행할 뿐....\n"
                                    #     "기획하다보니 프로토타입을 위해 정말 오랜만에 코드를 작성했다.\n"
                                    #     "FastAPI를 통해서 간단한 GPT 호출 코드를 작성했는데 단순한 코드 몇 줄 작성하는 것도 왜 이렇게 재미있는지.. 막상 코딩할 땐 기획 설계가 좋았다고 느끼겠지?\n"
                                    #     "내일 컨설팅이 잡혀있는데 걱정되기도 하고, GPT 4의 결과를 보니까 괜찮은 것 같기도 하고... 쓰다보니 또 걱정 먼저 하는 것 같다. 걱정좀 하지 말자. 배째라는 마인드\n"
                                    #     "퇴근하고 하늘을 보니 산책을 참을 수 없었다. 산책하는 겸 서브웨이도 사왔다. BLT에 올리브 폭탄으로다가 히히 내일아침이당\n"
                                    #     "오늘 포트폴리오 정리랑 채용공고 리스트업 하려고 했는데.. 공통 리드미 정리를 위한 GIF 파일 뽑다보니 시간이 다 갔다.\n" 
                                    #     "미친 포토샵 램 사용량이 16기가를 넘어서 터지다니 이게 프로그램인가 최적화좀 잘 하지;;\n"
                                    #     "주말까지 이력서도 가다듬고, 자소서도 써야하니 이번 주는 특히나 시간 분배 잘 해야겠다. 내일도 화이텡~~~~~~~~~\n"
                                      "반려동물: 고양이"
                                    },
                        {"role": "user", "content": request.diary }, 
            ],
            temperature=1, 
            max_tokens=1500,
            top_p=1, 
            frequency_penalty=0.0,
            presence_penalty=0.0, 
        )

    def getDiary(self):
        return {"data" : self.response['choices'][0]['message']['content']}

@app.post("/diary")
def diary(request: Request):
    return Response(request).getDiary()

if __name__ == "__main__":
    uvicorn.run(app, host="localhost", port=8000)