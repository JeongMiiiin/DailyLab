from transformers import pipeline

styles = ['문어체','구어체','안드로이드','아재','채팅',
    '초등학생','이모티콘','enfp','신사','할아버지','할머니','중학생',
    '왕','나루토','선비','소심한','번역기']

model = pipeline(
    'text2text-generation',
    model='heegyu/kobart-text-style-transfer'
)

def transfer_text_style(text, target_style, **kwargs):
  input = f"{target_style} 말투로 변환:{text}"
  out = model(input, max_length=64, **kwargs)

  return out[0]['generated_text']

text = """어제 밤늦게까지 자소서를 쓰고 자서 일어났을 때 많이 피곤했다. 일어나서 공부를 하려고 했지만 많이 하지는 못했다.
            "대신에 어제 작성한 자소서를 마지막으로 읽어보면서 오타를 수정하고 최종지원 버튼을 눌렀다.
            "최종지원버튼을 눌렀을 때, “정말 제출하시겠습니까?”라고 물어볼 줄 알았는데 바로 제출이 되어 많이 당황스러웠다.
            "이후에 토스트를 구워먹고 준비를 해서 교육장에 갔다.
            "오늘은 월요일이어서 지라와 gitlab도 새로 생성해야 했고 조례도 들어서 정말 많이 바빴다. 또 13시에 컨설턴트님과 팀미팅까지 있어서 아이디어를 구체화하는데에도 많은 힘을 썼다.
            "오전을 바쁘게 보내고 밥을 먹으러 갔는데 오늘의 메뉴는 돈육불고기와 왕새우튀김우동이었다. 돈육불고기는 짜긴 했지만 맛있었다. 그리고 팀원들과 산책을 하려는데 너무 더워서 숏컷으로 바로 들어왔다.
            "실내에 들어오니까 너무 상쾌해서 다시 기분이 좋았다.
            "팀미팅을 하고나ㅉ서는 할 일이 명확히 보였다. 그래서 해당 내용으로 팀원들과 다시 회의를 진행했고 역할을 나눴다. 그렇게 오후를 보내고 나서 간단한 팀회고를 한 후에 집에 돌아왔다.
            "집에 돌아와서는 저녁을 먹고오늘 쓰고 싶었던 기업인 한화투자증권 자소서를 시작했고, 또 쓸만한 기업들을 찾았고 언제까지 써야하는지 기록해서 정리해두었다. 내일도 열심히 살아야지."""
# for style in styles:
#   transfer_text_style(text, style)

for s in text.split("."):
    v = transfer_text_style(s, styles[9])
    print(v)