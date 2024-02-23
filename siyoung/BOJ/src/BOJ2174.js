const fs = require('fs');
const input = fs.readFileSync("./input.txt").toString().trim().split('\n');
// const input = fs.readFileSync("/dev/stdin").toString().trim().split('\n');
const [a, b] = input[0].split(' ').map(Number);
const [n, m] = input[1].split(' ').map(Number);
const map = Array.from(Array(b), () => new Array(a).fill(0));

const dir = {'N':{i:1, j:0}, 'E':{i:0, j:1}, 'S':{i:-1, j:0}, 'W':{i:0, j:-1}};
let idx = 1;
let pos = [{}];
for(let i=2; i<n+2; i++){
    let [x, y, d] = input[i].trim().split(' ');
    map[y-1][x-1] = idx;
    pos.push({y:y-1, x:x-1, d:d});
    idx++;
}
const q = [];
for(let i=n+2; i<n+m+2; i++){
    let [target, move, cnt] = input[i].trim().split(' ');
    q.push({target:target, move:move, cnt:cnt});
}
let flag = true;
L: while(q.length>0){
    let cn = q.shift();
    for(let c=0; c<cn.cnt; c++){
        map[pos[parseInt(cn.target)].y][pos[parseInt(cn.target)].x] = 0;
        let nd = pos[parseInt(cn.target)].d;
        if(cn.move==='L'){
            if(nd==='N') pos[parseInt(cn.target)].d = 'W'
            else if(nd==='E') pos[parseInt(cn.target)].d = 'N'
            else if(nd==='S') pos[parseInt(cn.target)].d = 'E'
            else if(nd==='W') pos[parseInt(cn.target)].d = 'S'
        }
        else if(cn.move==='R'){
            if(nd==='N') pos[parseInt(cn.target)].d = 'E'
            else if(nd==='E') pos[parseInt(cn.target)].d = 'S'
            else if(nd==='S') pos[parseInt(cn.target)].d = 'W'
            else if(nd==='W') pos[parseInt(cn.target)].d = 'N'
        }
        else if(cn.move==='F'){
            let ni = pos[parseInt(cn.target)].y;
            let nj = pos[parseInt(cn.target)].x;
            // console.log(pos[parseInt(cn.target)], dir[pos[parseInt(cn.target)].d]);
            ni = ni+dir[pos[parseInt(cn.target)].d].i;
            nj = nj+dir[pos[parseInt(cn.target)].d].j;
            if(ni<0 || ni>=b || nj<0 || nj>=a) {
                console.log("Robot "+parseInt(cn.target)+" crashes into the wall");
                flag = false;
                break L;
            }
            else if(map[ni][nj]!==0){
                console.log("Robot "+parseInt(cn.target)+" crashes into robot "+map[ni][nj]);
                flag = false;
                break L;
            }
            else{
                map[ni][nj] = parseInt(cn.target);
                pos[parseInt(cn.target)].y = ni;
                pos[parseInt(cn.target)].x = nj;
            }
        }
    }
}
// console.log(map);
if(flag) console.log("OK");
// console.log(map.join('\n'));