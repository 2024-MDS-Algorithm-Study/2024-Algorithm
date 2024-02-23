let fs = require('fs');
let input = fs.readFileSync('./input.txt').toString().trim().split('\n');
// let input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let n = input[0];
const dir = [[-2, -1], [-2, 1], [-1, 2], [1, 2], [2, 1], [2, -1], [1, -2], [-1, -2]];
for(let t=0; t<n; t++){
    let l = parseInt(input[t*3+1]);
    let [curX, curY] = input[t*3+2].split(' ').map(Number);
    let [tarX, tarY] = input[t*3+3].split(' ').map(Number);
    if(curX === tarX && curY === tarY) {
        console.log(0);
        continue;
    }
    let v = Array.from(Array(l), () => new Array(l).fill(false));
    v[curX][curY] = true;
    let cn
    let q = [{curX:curX, curY:curY, cnt:0}];
    L: while(q.length>0){
        cn = q.shift();
        if(cn.curX === tarX && cn.curY === tarY){
            console.log(cn.cnt);
            break;
        }
        for(let d=0; d<8; d++){
            let nx = cn.curX+dir[d][0];
            let ny = cn.curY+dir[d][1];
            if(nx>=0 && nx<l && ny>=0 && ny<l && !v[nx][ny]){
                v[nx][ny] = true;
                q.push({curX:nx, curY:ny, cnt:cn.cnt+1});
            }
        }
    }
}