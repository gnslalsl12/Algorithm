let N;
let Nums;
let tempN;
let K;
let OrgSum;
const CharNumArray = Array(36).fill(0n);

const convWholeBn = (input) => {
  //BigInt로 변환한 36진수값
  let sum = 0n;
  for (let i = 0; i < input.length; i++) {
    sum += 36n ** BigInt(input.length - 1 - i) * BigInt(parseInt(input.charAt(i), 36));
  }
  return sum;
};

const convBn = (count, pow) => {
  //pow자리에 count값이 가지는 값 반환
  let rtn = 1n;
  for (let i = 0; i < pow; i++) {
    rtn *= 36n;
  }
  return rtn * BigInt(35 - count);
};

const getWholeSubs = () => {
  //각 알파벳을 Z로 바꿨을 때 추가되어야하는 수치값 배열
  for (const num of Nums) {
    let tempNum = "";
    for (let i = 0; i < num.length; i++) {
      const charNum = parseInt(num.charAt(i), 36); //문자 하나를 10진수로 변환
      CharNumArray[charNum] += convBn(charNum, num.length - 1 - i); //해당 문자를 Z로 바꿨을 때 추가해야하는 값 추가
    }
  }
  CharNumArray.sort((x1, x2) => (x1 > x2 ? -1 : 1)); //내림차순 정렬
};

const getResult = () => {
  for (let i = 0; i < K; i++) {
    if (CharNumArray[i] === 0) break;
    OrgSum += CharNumArray[i];
  }
  console.log(OrgSum.toString(36).toUpperCase());
};

const init = (line) => {
  if (N === undefined) {
    N = parseInt(line);
    tempN = 0;
    Nums = new Array(N);
    OrgSum = 0n;
  } else if (tempN < N) {
    Nums[tempN++] = line;
    OrgSum += convWholeBn(line);
  } else {
    K = parseInt(line);
  }
};

const solv = () => {
  getWholeSubs();
  getResult();
};

require("readline")
  .createInterface(process.stdin, process.stdout)
  .on("line", (line) => {
    init(line);
  })
  .on("close", () => {
    solv();
    process.exit;
  });