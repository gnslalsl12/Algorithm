const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

let N = 0;
const Map = [];
const Deltas = [[-1,0],[0,1],[1,0],[0,-1]];
let Highest = 0;
let MaxCount = 0;

rl.on('line', function(line){
    if(N === 0){
        N = parseInt(line);
    }else{
        const inputArr = line.split(' ').map((inputNum) => {
            const intNum = parseInt(inputNum);
            Highest = Math.max(Highest, intNum);
            return parseInt(intNum)
        });
        Map.push(inputArr);
        
        if(Map.length === N){
            rl.close();
        }
    }
}).on('close', function(){
    function isIn(i,j){
        return i >= 0 && i < N && j >= 0 && j < N;
    }
    
    function setSection(i,j, vis, height){
        let bfsQ = [i*N + j];
        vis[i][j] = true;
        while(bfsQ.length > 0){
            const loc = bfsQ.shift();
            const i = Math.floor(loc/N);
            const j = loc%N;
            for(const dir of Deltas){
                const nextI = i + dir[0];
                const nextJ = j + dir[1];
                if(!isIn(nextI, nextJ) || vis[nextI][nextJ] || Map[nextI][nextJ] <= height) continue;   //방문 / 비랑 같거나 낮음
                vis[nextI][nextJ] = true;
                bfsQ.push(nextI*N + nextJ);
            }
        }
    }
    
    function getSectionCount(height){
        let vis = Array.from(Array(N), () => Array(N).fill(false));
        let sectionCount = 0;
        for(let i = 0; i < N; i++){
            for(let j = 0; j < N; j++){
                if(!vis[i][j] && Map[i][j] > height){   //방문X, 비보다 높은 높이 발견견
                    setSection(i,j, vis, height);
                    sectionCount++;
                }
            }
        }
        MaxCount = Math.max(MaxCount, sectionCount);
    }
    
    for(let h = 0; h <= Highest; h++){
        getSectionCount(h);
    }
    
    console.log(MaxCount);
    
    process.exit();
})