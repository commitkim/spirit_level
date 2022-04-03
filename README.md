# spirit_level
2021.02.15 Assignment
2022.03.22

수평계 앱 만들기 / 앱 등록하기
- https://play.google.com/store/apps/details?id=bubblelevel.level.leveltool.leveler 예제와 비슷하게 
- 예제 앱처럼 3가지 유형의 수평계 UI 만들기 
- 사용자가 설치할 매력을 느낄 정도의 앱 완성도로 만들기 (제목, 썸네임, 설명 ... 포함)
- 결과물: repo 주소, 마켓 주소


<img src = "https://user-images.githubusercontent.com/65940401/161447362-c72ce5ec-2ecc-4438-82ac-f6e3365a7d78.jpg" width="30%" height="30%">

## Structure
    .
    ├── CenterLevel             # Center spirit level in center
    ├── Circle                  # Class for circle that move by sensor value in spirit level
    ├── HorizontalLevel         # Horizontal spirit level in top
    ├── Level                   # Data is related to the network.
    ├── MainActivity            # MainActivity of this app
    └── VerticalLevel           # Vertical spirit level in left
