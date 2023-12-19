let N, M;
let TempN, TempM;
let NList;
let MList;
let Parent;

const getParent = (child) => {
  if (Parent[child] === child) {
    return child;
  }
  return getParent(Parent[child]);
};

const setParent = (parent, child) => {
  const pParent = getParent(parent);
  const cParent = getParent(child);
  if (pParent === cParent) {
    //이긴왕국의 종주국이 진 왕국 (종주국 c를 상대로 이긴 속국 p)
    Parent[parent] = Parent[cParent] = parent;
  } else {
    Parent[cParent] = Parent[pParent];
  }
};

const setWarCases = () => {
  for (const war of MList) {
    const win = war[war[2] - 1];
    const lose = war[war[2] % 2];
    setParent(NList.get(win), NList.get(lose));
  }
};

const getOriginKingdoms = () => {
  const tempResults = [];
  for (const kingdom of NList) {
    if (Parent[kingdom[1]] === kingdom[1]) {
      tempResults.push(kingdom[0]);
    }
  }
  tempResults.sort();
  console.log(tempResults.length);
  for (const result of tempResults) {
    console.log("Kingdom of " + result);
  }
};

const init = (line) => {
  if (N === undefined) {
    [N, M] = line.split(" ").map(Number);
    NList = new Map();
    MList = Array(M);
    TempN = TempM = 0;
    Parent = Array.from({ length: N }, (_, i) => i);
  } else if (TempN < N) {
    NList.set(line.split("Kingdom of ")[1], TempN++);
  } else if (TempM < M) {
    MList[TempM++] = line.split(/Kingdom of |\,/).filter((x) => x !== "");
  }
};

const solv = () => {
  setWarCases();
  getOriginKingdoms();
};

require("readline")
  .createInterface(process.stdin, process.stdout)
  .on("line", (line) => {
    init(line);
  })
  .on("close", () => {
    solv();
    process.exit();
  });