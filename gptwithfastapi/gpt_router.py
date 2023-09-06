import config
import openai
import time

from fastapi import APIRouter

from pydantic import BaseModel

class gptInput(BaseModel):
    diary: str
    todo: str
    information: str


openai.api_key = config.gpt_api_key_coco

router = APIRouter()

@router.get("/gpt")
def gpt():
    start = time.time()

    response = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=[
            {
                "role": "system", "content": "You are a helpful assistant."
            },
            {
                "role": "user",
                "content": "아래 일기의 요즘 10대들이 사용할만한 말투를 사용하여 새로운 내용의 일기를 작성할거야.\n"
                           "말투만 사용하는 것이고, 아래 일기의 내용이 새로 작성할 일기의 내용에 절대 절대 포함되면 안돼.\n"

            },
            {
                "role": "assistant",
                "content": "2023년 9월 4일 (월) \n"
                           "어제 밤늦게까지 자소서를 쓰고 자서 일어났을 때 많이 피곤했다. 일어나서 공부를 하려고 했지만 많이 하지는 못했다. "
                           "대신에 어제 작성한 자소서를 마지막으로 읽어보면서 오타를 수정하고 최종지원 버튼을 눌렀다. "
                           "최종지원버튼을 눌렀을 때, '정말 제출하시겠습니까?'라고 물어볼 줄 알았는데 바로 제출이 되어 많이 당황스러웠다. "
                           "이후에 토스트를 구워먹고 준비를 해서 교육장에 갔다. "
                           "오늘은 월요일이어서 지라와 gitlab도 새로 생성해야 했고 조례도 들어서 정말 많이 바빴다. 또 13시에 컨설턴트님과 팀미팅까지 있어서 아이디어를 구체화하는데에도 많은 힘을 썼다. "
                           "오전을 바쁘게 보내고 밥을 먹으러 갔는데 오늘의 메뉴는 돈육불고기와 왕새우튀김우동이었다. 돈육불고기는 짜긴 했지만 맛있었다. "
                           "그리고 팀원들과 산책을 하려는데 너무 더워서 숏컷으로 바로 들어왔다. 실내에 들어오니까 너무 상쾌해서 다시 기분이 좋았다. "
                           "팀미팅을 하고나서는 할 일이 명확히 보였다. 그래서 해당 내용으로 팀원들과 다시 회의를 진행했고 역할을 나눴다. "
                           "그렇게 오후를 보내고 나서 간단한 팀회고를 한 후에 집에 돌아왔다. "
                           "집에 돌아와서는 저녁을 먹고 오늘 쓰고 싶었던 기업인 한화투자증권 자소서를 시작했고, "
                           "또 쓸만한 기업들을 찾았고 언제까지 써야하는지 기록해서 정리해두었다. 내일도 열심히 살아야지."
            },
            {
                "role": "user",
                "content": "아래는 TODO리스트야. 내일 할 일이므로, 내일 일기에 포함되어야 해."
            },
            {
                "role": "assistant",
                "content": "한화투자증권 자소서 완성\n"
                           "공통 이력서 첨삭\n"
                           "개인 공부하기\n"
                           "저녁에 돈까스먹기"
            },
            {
                "role": "user",
                "content": "아래는 일기의 주인공인 '나'에 관한 정보들이야. 내일의 일기를 작성할 때 이러한 정보를 활용해도 좋아. 대신 아래 정보를 직접적으로 일기에 말하면 안돼. 간접적으로 사용해줘."
            },
            {
                "role": "assistant",
                "content": "성격: 내향적, 감각적, 감정적, 인식적\n"
                           "키: 168cm\n"
                           "몸무게: 62kg\n"
                           "성별: 남자\n"
                           "최근에 본 감명깊은 영화: 오펜하이머\n"
                           "탕수육을 먹는 방식 : 부먹\n"
                           "좋아하는 음악: She's gone\n"
                           "직업: 개발자\n"
            },
            {
                "role": "user",
                "content":
                    "내가 내일 잠에 들기 전에 일기를 작성한 것 처럼 작성해줘. \n"
                    # "일기의 어투는 제일 위에 제공한 일기의 형식. 절대 그 위의 일기의 내용을 새 일기에 적용하면 안돼.\n "
                    "그리고 내가 제공한 TODO는 반드시 들어가야 해. 대신 일기에 TODO 리스트라는 언급을 적지 마. 그리고 주인공인 나의 정보는 반드시 들어갈 필요는 없어. 그 정보가 새로 작성할 일기에 직접적으로 들어가면 안돼고, 간접적으로 스며들어야해. 일기니까 일기 중간중간에 감정이 섞여있으면 좋을 것 같아.\n"
                    "일기의 내용 말고 사족은 다 작성하지 말아줘.\n"
                    "일기처럼 느껴지기 위해 일기 중간중간에 감정적인 표현이 자주 들어가야해.\n"
                    "그럼, 이러한 조건으로 내일 밤에 작성한 일기를 보여줘!"
            }
        ],
        temperature=0.7,  # 상상력 조절
        max_tokens=2000,  # 반환받을 문장 크기 조절
        top_p=1,  # 답변의 무작위성
        frequency_penalty=0.0,  # 특정 단어나 구를 반복하지 않도록
        presence_penalty=0.0  # 반복적인 텍스트 생성 방지
    )

    print(response)
    print(response['choices'][0]['message']['content'])
    end = time.time()
    print(end - start)

    return response['choices'][0]['message']['content']

@router.post("/gpt/post")
def gptPost(input: gptInput):
    start = time.time()

    response = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=[
            {
                "role": "system", "content": "You are a helpful assistant."
            },
            {
                "role": "user",
                "content": "아래 일기의 요즘 10대들이 사용할만한 말투를 사용하여 새로운 내용의 일기를 작성할거야.\n"
                           "말투만 사용하는 것이고, 아래 일기의 내용이 새로 작성할 일기의 내용에 절대 절대 포함되면 안돼.\n"

            },
            {
                "role": "assistant",
                "content": input.diary
            },
            {
                "role": "user",
                "content": "아래는 TODO리스트야. 내일 할 일이므로, 내일 일기에 포함되어야 해."
            },
            {
                "role": "assistant",
                "content": input.todo
            },
            {
                "role": "user",
                "content": "아래는 일기의 주인공인 '나'에 관한 정보들이야. 내일의 일기를 작성할 때 이러한 정보를 활용해도 좋아. 대신 아래 정보를 직접적으로 일기에 말하면 안돼. 간접적으로 사용해줘."
            },
            {
                "role": "assistant",
                "content": input.information
            },
            {
                "role": "user",
                "content":
                    "내가 내일 잠에 들기 전에 일기를 작성한 것 처럼 작성해줘. \n"
                    # "일기의 어투는 제일 위에 제공한 일기의 형식. 절대 그 위의 일기의 내용을 새 일기에 적용하면 안돼.\n "
                    "그리고 내가 제공한 TODO는 반드시 들어가야 해. 대신 일기에 TODO 리스트라는 언급을 적지 마. 그리고 주인공인 나의 정보는 반드시 들어갈 필요는 없어. 그 정보가 새로 작성할 일기에 직접적으로 들어가면 안돼고, 간접적으로 스며들어야해. 일기니까 일기 중간중간에 감정이 섞여있으면 좋을 것 같아.\n"
                    "일기의 내용 말고 사족은 다 작성하지 말아줘.\n"
                    "일기처럼 느껴지기 위해 일기 중간중간에 감정적인 표현이 자주 들어가야해.\n"
                    "그럼, 이러한 조건으로 내일 밤에 작성한 일기를 보여줘!"
            }
        ],
        temperature=1,  # 상상력 조절
        max_tokens=2000,  # 반환받을 문장 크기 조절
        top_p=1,  # 답변의 무작위성
        frequency_penalty=0.0,  # 특정 단어나 구를 반복하지 않도록
        presence_penalty=0.0  # 반복적인 텍스트 생성 방지
    )

    print(response)
    print(response['choices'][0]['message']['content'])
    end = time.time()
    print(end - start)

    return response['choices'][0]['message']['content']