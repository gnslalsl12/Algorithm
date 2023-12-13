let Puzz = 1;
let N;
let Map;
let Dominos;
let SecVis;
let RowVis;
let ColVis;
let Done;
const Deltas = [
  [-1, 0],
  [0, 1],
  [1, 0],
  [0, -1],
];

function convert(input) {
  return (input.charCodeAt(0) - 65) * 9 + input.charCodeAt(1) - 49;
}

function setOnMapNumber(value, index) {
  const [row, col] = [(index / 9) | 0, index % 9];
  Map[row][col] = value;
  SecVis[(((row / 3) | 0) * 3 + col / 3) | 0][value] = true;
  RowVis[row][value] = true;
  ColVis[col][value] = true;
}

function isAbleTemp(value, row, col) {
  //row, col 위치에 value가 놓일 수 있는가 (맵 상 확인)
  return (
    Map[row][col] === 0 &&
    !SecVis[(((row / 3) | 0) * 3 + col / 3) | 0][value] &&
    !RowVis[row][value] &&
    !ColVis[col][value]
  );
}

function isOnDomino(valueA, valueB) {
  //가능한 도미노 값 쌍인가
  return Dominos[valueA * 10 + valueB] || Dominos[valueB * 10 + valueA];
}

function setOnMap(valueA, indexA, valueB, indexB, put) {
  //indexA 에 valueA,를, indexB 에 valueB를 put하기
  const [rA, cA, rB, cB] = [(indexA / 9) | 0, indexA % 9, (indexB / 9) | 0, indexB % 9];
  Map[rA][cA] = put ? valueA : 0;
  Map[rB][cB] = put ? valueB : 0;
  Dominos[valueA * 10 + valueB] = Dominos[valueB * 10 + valueA] = put;
  SecVis[(((rA / 3) | 0) * 3 + cA / 3) | 0][valueA] = SecVis[(((rB / 3) | 0) * 3 + cB / 3) | 0][
    valueB
  ] = put;
  RowVis[rA][valueA] = RowVis[rB][valueB] = put;
  ColVis[cA][valueA] = ColVis[cB][valueB] = put;
}

function isIn(i, j) {
  return i >= 0 && i < 9 && j >= 0 && j < 9;
}

function setDominos(index) {
  const [i, j] = [(index / 9) | 0, index % 9];
  if (index > 80) {
    //무리 없이 끝까지 채워짐
    Done = true;
    return;
  }
  if (Map[i][j] !== 0) {
    //이미 채워진 공간
    setDominos(index + 1);
    return;
  }
  //채워야하는 공간 발견
  for (let numA = 1; numA <= 9; numA++) {
    if (isAbleTemp(numA, i, j)) {
      //i, j에 numA를 채울 수 있음
      for (let dir of Deltas) {
        const nextI = i + dir[0];
        const nextJ = j + dir[1];
        if (!isIn(nextI, nextJ) || Map[nextI][nextJ] !== 0) continue;
        for (let numB = 1; numB <= 9; numB++) {
          if (numA !== numB && isAbleTemp(numB, nextI, nextJ) && !isOnDomino(numA, numB)) {
            //i,j 바로 옆의 nextI, nextJ에 numB를 채울 수 있음
            setOnMap(numA, i * 9 + j, numB, nextI * 9 + nextJ, true); //i,j에 numA, nextI,nextJ에 numB를 채우고 방문 체크
            setDominos(index + 1); //다음 위치 탐색시작
            if (Done) {
              return;
            } else {
              setOnMap(numA, i * 9 + j, numB, nextI * 9 + nextJ, false);
            }
          }
        }
      }
    }
  }
}

function printMap() {
  console.log("Puzzle " + Puzz++);
  const printOutput = Map.map((row) => row.join("")).join("\n");
  console.log(printOutput);
}

function solv() {
  setDominos(0);
  printMap();
}

function init(line) {
  if (line.length === 2) {
    Done = false;
    N = parseInt(line);
    Map = Array.from({ length: 9 }, () => Array(9).fill(0));
    Dominos = Array(100).fill(false);
    SecVis = Array.from(Array(9), () => Array(10).fill(false));
    RowVis = Array.from(Array(9), () => Array(10).fill(false));
    ColVis = Array.from(Array(9), () => Array(10).fill(false));
  } else if (line.length === 1) {
    return;
  } else {
    if (N > 0) {
      N--;
      const ss = ([numA, locA, numB, locB] = line.split(" ").map((v, i) => {
        if (i % 2 === 0) {
          return parseInt(v);
        } else {
          return convert(v);
        }
      }));
      setOnMap(numA, locA, numB, locB, true);
    } else if (N === 0) {
      line.split(" ").map((v, i) => {
        setOnMapNumber(i + 1, convert(v));
      });
      solv();
    }
  }
}

require("readline")
  .createInterface(process.stdin, process.stdout)
  .on("line", (line) => {
    init(line);
  })
  .on("close", () => {
    process.exit();
  });