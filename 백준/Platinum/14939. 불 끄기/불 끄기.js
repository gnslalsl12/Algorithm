const readline = require("readline");
const interface = readline.createInterface(process.stdin, process.stdout);

let tempN = 0;
const Map = [];
let ResultCount;

const Deltas = [
  [0, 0],
  [-1, 0],
  [0, 1],
  [1, 0],
  [0, -1],
];

const init = (line) => {
  //입력 처리
  let tempValue = 0;
  for (let m = 9; m >= 0; m--) {
    if (line[m] === "O") {
      tempValue |= 1 << m;
    }
  }
  Map[tempN++] = tempValue;
  if (tempN === 10) interface.close();
};

const solv = () => {
  getResult(); //Result 구하기
  console.log(ResultCount === 200 ? -1 : ResultCount);
};

const getResult = () => {
  ResultCount = 200;
  subsetPrsdMap(
    //첫줄을 한 칸씩 부분집합으로 누르는 모든 경우
    9,
    Array.from(Array(10), () => 0),
    0
  );
};

const subsetPrsdMap = (index, tempPrsd, prsdCount) => {
  //부분집합으로 첫 줄 누르는 모든 케이스
  if (index < 0) {
    //첫 줄을 누르는 케이스 하나씩
    setPrsd(0, tempPrsd, prsdCount, JSON.parse(JSON.stringify(Map)));
    return;
  }

  tempPrsd[0] &= ~(1 << index);
  subsetPrsdMap(index - 1, tempPrsd, prsdCount);
  tempPrsd[0] |= 1 << index;
  subsetPrsdMap(index - 1, tempPrsd, prsdCount + 1);
};

const setPrsd = (row, prsdMap, prsdCount, thisMap) => {
  //row행 기준으로 prsdMap을 탐색하여 !=0 발견시 thisMap의 해당 위치 사방 뒤집기
  if (prsdCount > ResultCount) {
    //결과의 카운트보다 많이 눌러야하는 경우는 패스
    return;
  }
  for (let m = 9; m >= 0; m--) {
    if ((prsdMap[row] & (1 << m)) != 0) {
      //row,n이 눌러짐
      for (const dir of Deltas) {
        //사방 뒤집기
        const nextN = row + dir[0];
        const nextM = m + dir[1];
        if (!isIn(nextN, nextM)) continue;
        thisMap[nextN] ^= 1 << nextM;
      }
    }
  }
  if (row === 9) {
    //마지막행까지 다 눌렀을 때
    if (thisMap[9] === 0 && ResultCount > prsdCount) {
      //마지막 행 붕어빵 모두 뒤집어졌을 때
      ResultCount = prsdCount;
    }
    return;
  }
  searchNextPrs(row + 1, prsdMap, prsdCount, thisMap); //다음 행 뒤집을 위치 체크
};

const searchNextPrs = (row, prsdMap, prsdCount, thisMap) => {
  //row행 기준으로 윗 행의 thisMap상에서 안 뒤집힌 붕어빵이 있으면 prsdMap에 눌러줌 체크, prsdCount+1
  for (let m = 9; m >= 0; m--) {
    if ((thisMap[row - 1] & (1 << m)) != 0) {
      //윗행에 덜 뒤집힌 붕어빵
      prsdMap[row] |= 1 << m; //해당 행, 열에 뒤집기 체크
      prsdCount++;
    } else {
      prsdMap[row] &= ~(1 << m); //뒤집지 않음 체크
    }
  }
  setPrsd(row, prsdMap, prsdCount, thisMap);
};

const isIn = (n, m) => {
  return n >= 0 && n < 10 && m >= 0 && m < 10;
};

interface
  .on("line", (line) => {
    init(line);
  })
  .on("close", () => {
    solv();
    process.exit();
  });