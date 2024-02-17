const readline = require("readline");
const interface = readline.createInterface(process.stdin, process.stdout);

let M, N;
let tempM;
const Map = [];
let Result;
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
  if (N === undefined) {
    [M, N] = line.split(" ").map(Number);
    tempM = 0;
  } else {
    Map[tempM++] = parseInt(line.split(" ").join(""), 2);
    if (tempM === M) interface.close();
  }
};

const solv = () => {
  getResult(); //Result 구하기
  if (!Result) {
    console.log("IMPOSSIBLE");
  } else {
    printMap(Result);
  }
};

const printMap = (array) => {
  //10진수 배열 2진수 값으로 출력
  for (let m = 0; m < M; m++) {
    let outputLine = "";
    for (let n = N - 1; n >= 0; n--) {
      outputLine += (array[m] & (1 << n)) !== 0 ? "1 " : "0 ";
    }
    console.log(outputLine);
  }
};

const getResult = () => {
  ResultCount = N * M + 5;
  subsetPrsdMap(
    //첫줄을 한 칸씩 부분집합으로 누르는 모든 경우
    N - 1,
    Array.from(Array(M), () => 0),
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
  for (let n = N - 1; n >= 0; n--) {
    if ((prsdMap[row] & (1 << n)) != 0) {
      //row,n이 눌러짐
      for (const dir of Deltas) {
        //사방 뒤집기
        const nextM = row + dir[0];
        const nextN = n + dir[1];
        if (!isIn(nextM, nextN)) continue;
        thisMap[nextM] ^= 1 << nextN;
      }
    }
  }
  if (row === M - 1) {
    //마지막행까지 다 눌렀을 때
    if (thisMap[M - 1] === 0 && ResultCount > prsdCount) {
      //마지막 행 붕어빵 모두 뒤집어졌을 때
      //오름차순 맵 확인이므로 카운트가 더 적을 때만
      ResultCount = prsdCount;
      Result = JSON.parse(JSON.stringify(prsdMap));
    }
    return;
  }
  searchNextPrs(row + 1, prsdMap, prsdCount, thisMap); //다음 행 뒤집을 위치 체크
};

const searchNextPrs = (row, prsdMap, prsdCount, thisMap) => {
  //row행 기준으로 윗 행의 thisMap상에서 안 뒤집힌 붕어빵이 있으면 prsdMap에 눌러줌 체크, prsdCount+1
  for (let n = N - 1; n >= 0; n--) {
    if ((thisMap[row - 1] & (1 << n)) != 0) {
      //윗행에 덜 뒤집힌 붕어빵
      prsdMap[row] |= 1 << n; //해당 행, 열에 뒤집기 체크
      prsdCount++;
    } else {
      prsdMap[row] &= ~(1 << n); //뒤집지 않음 체크
    }
  }
  setPrsd(row, prsdMap, prsdCount, thisMap);
};

const isIn = (m, n) => {
  return m >= 0 && m < M && n >= 0 && n < N;
};

interface
  .on("line", (line) => {
    init(line);
  })
  .on("close", () => {
    solv();
    process.exit();
  });