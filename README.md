# 로또
## 진행 방법
* 로또 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 기능 목록 (for step1)
* InputView
* ResultView

* Main
* LottoGame
  - 로또 구입금액 입력 - 발행된 티켓 return
  - 당첨번호 입력 - 당첨확인 후 결과 return
* LottoTicket(일급콜렉터)
  - 로또번호 생성 - 자동 6개
  - 로또번호 생성 - 수동 6개 (6개 숫자 parameter)
  - 당첨확인 - 등수반환
  - 유효성 검사 - 갯수(6개)
  - 유효성 검사 - 구성(콤마, 범위내 숫자)
