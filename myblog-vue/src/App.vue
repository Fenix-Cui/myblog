<template>
  <div id="app">
    <el-backtop class="backtop"></el-backtop>
    <div><canvas></canvas></div>
    <div class="background"><img src="static/images/bg01.jpg"/></div>
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: 'App',
  components: {

  },
  data() {
    return {};
  },
  methods: {},
  mounted() {

    window.requestAnimationFrame = window.requestAnimationFrame || window.mozRequestAnimationFrame || window.webkitRequestAnimationFrame;

    let canvas = document.querySelector("canvas");
    canvas.width = window.innerWidth
    canvas.height = window.innerHeight;
    let ctx = canvas.getContext("2d");
    ctx.globalCompositeOperation = "source-over";
    let particles = [];
    let pIndex = 0;
    let x, y, frameId;

    function Dot(x,y,w,h){
      this.x = x;
      this.y = y;
      this.width = w;
      this.height = h;
      particles[pIndex] = this;
      this.id = pIndex;
      pIndex++;
      this.life = 0;
      this.maxlife = getRandom(1,3);
      this.alpha = getRandom(0.01,0.2);

    };

    Dot.prototype.draw = function(x, y){
      ctx.strokeStyle = "rgba(125, 125, 125, " + this.alpha +""+ ")";
      ctx.beginPath();
      ctx.moveTo(this.x+this.x/2, this.y+this.y/2);
      ctx.lineTo(this.x+this.x/2+this.width/2, this.y+this.y/2+this.height);
      ctx.closePath();
      ctx.stroke();
      this.life++;
      if(this.life >= this.maxlife){
        delete particles[this.id];
      }
    }

    window.addEventListener("resize", function(){
      canvas.width = window.innerWidth;
      canvas.height = window.innerHeight;
      x = canvas.width / 2;
      y = canvas.height / 2;
    });

    function loop(){
      ctx.clearRect(0,0, canvas.width, canvas.height);
      for (let i = 0; i < 600; i++) {
        new Dot(canvas.width*Math.random()*2-canvas.width/2, canvas.height*Math.random(), getRandom(-15,15), getRandom(70,150));

      }

      for(let i in particles){
        particles[i].draw();
      }
      frameId = requestAnimationFrame(loop);
    }

    loop();

    function getRandom(min, max) {
      return Math.random() * (max - min) + min;
    }

  }
}


</script>

<style>

  .background{
    width: 100%;
    height: 100%;
    z-index: -1;
    position: fixed;
  }

  *{
    margin: 0;
    /*z-index: -1;*/
  }
  canvas {
    background: rgba(0,0,0,0);
    height: 100%;
    width: 100%;
    position: absolute;
    /*background-image: url("static/images/bg.jpg");*/
    background-size: cover;
    /*z-index: -1;*/
  }


</style>
