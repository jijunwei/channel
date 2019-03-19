window.onload = function() {

    /**
     * 图一（左上--折线图）：近一个月已放款总额及贷款本金未回收余额趋势图
     */
    // 基于准备好的dom，初始化echarts实例
    var pieNode = document.getElementById('pieOne');
    var myChartPieOne = echarts.init(pieNode);
    var dateArr = []; //日期
    var paymentArr = []; //放款总额
    var recoveryArr = [];//待还款总额,待回收余额
    myChartPieOne.showLoading();
    function fetchPieOneData() {
        $.ajax({
            type: "POST",
            url: path+"/financialRegulation/paymentAmountByMonth.do",
            dataType: "json",
            success: function(result){
                // console.log('res--近一个月已放款总额---', result);
                for(var i=0;i<result.data.length;i++){
                    dateArr.push(result.data[i].date);
                    paymentArr.push(Number(result.data[i].payment));
                    recoveryArr.push(Number(result.data[i].recovery));
                }
                myChartPieOne.hideLoading();
                // console.log('dateArr--', dateArr);
                // console.log('paymentArr--', paymentArr);
                // console.log('recoveryArr--', recoveryArr);
                myChartPieOne.setOption( {
                    color: ['#c23531','#2f4554', '#61a0a8', '#d48265', '#91c7ae','#749f83',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3'],
                    title: {
                        text: '近一个月放款及回收额趋势图',
                        left: 'center',
                        top: 0
                    },
                    // smooth:true,
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data:['待回收余额','已放款总额'],
                        left: 'center',
                        bottom: 0,
                        padding: [0,0,10,0]
                    },
                    grid: {
                        left: '0.2%',
                        right: '10%',
                        bottom: '10%',
                        containLabel: true
                    },
                    toolbox: {
                        feature: {}
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: dateArr
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            name:'待回收余额',
                            type:'line',
                            label: {
                                normal: {
                                    show: true,
                                    position: [2,-15]
                                }
                            },
                            smooth: true,
                            data: recoveryArr
                        },
                        {
                            name:'已放款总额',
                            type:'line',
                            label: {
                                normal: {
                                    show: true,
                                    position: [2,-15]
                                }
                            },
                            smooth: true,
                            data: paymentArr
                        }
                    ]
                });
            },
            error:function (err) {
                // console.log('err---', err);
            }
        });
    }
    fetchPieOneData();


    /**
     *图三（左下--饼图）：当前已放款金额与百分比统计
     */
    var pieNodeTwo_lf = document.getElementById('pieTwo-lf');
    var myChartPieTwo_lf = echarts.init(pieNodeTwo_lf);
    var PieTwo_lfArr = [];
    myChartPieTwo_lf.hideLoading();
    function fetchPieTwo_lf() {
        $.ajax({
            type: "POST",
            url: path+"/financialRegulation/classificationByCity.do",
            dataType: "json",
            success: function(result){
                myChartPieTwo_lf.hideLoading();
                // console.log('res--城市---', result);
                for(var i=0,tempObj={};i<result.data.length;i++){
                    tempObj.value = result.data[i].cityAmount;
                    tempObj.name = result.data[i].cityName;
                    PieTwo_lfArr.push(tempObj);
                    tempObj={};
                }
                // console.log('PieTwo_lfArr---', PieTwo_lfArr);
                myChartPieTwo_lf.setOption({
                    color:['#A8CDF9','#436DA0', '#8EA3BB', '#AFFADB', '#A8A6F7','#F8D59F',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3'],
                    title : {
                        text: '当前已放款金额与百分比统计',
                        subtext: '城市',
                        x:'center',
                        top: 50
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    series : [
                        {
                            name: '访问来源',
                            type: 'pie',
                            label:{
                                formatter: "{b}\n{d}%",
                                color:'#333'
                            },
                            radius : '50%',
                            center: ['48%', '55%'],
                            data: PieTwo_lfArr,
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                });
            },
            error:function (err) {
                // console.log('err---', err);
            }
        });
    }
    fetchPieTwo_lf();



    var pieNodeTwo_rt = document.getElementById('pieTwo-rt');
    var myChartPieTwo_rt = echarts.init(pieNodeTwo_rt);
    var PieTwo_rtArr = [];
    myChartPieTwo_rt.hideLoading();
    function fetchPieTwo_rt() {
        $.ajax({
            type: "POST",
            url: path+"/financialRegulation/classificationByFlat.do",
            dataType: "json",
            success: function(result){
                myChartPieTwo_rt.hideLoading();
                // console.log('res--公寓---', result);
                for(var i=0,tempObj={};i<result.data.length;i++){
                    tempObj.value = result.data[i].flatAmount;
                    tempObj.name = result.data[i].flatName;
                    PieTwo_rtArr.push(tempObj);
                    tempObj={};
                }
                // console.log('PieTwo_rtArr---', PieTwo_rtArr);
                myChartPieTwo_rt.setOption({
                    color:['#A8CDF9','#436DA0', '#8EA3BB', '#AFFADB', '#A8A6F7','#F8D59F',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3'],
                    title : {
                        text: '当前已放款金额与百分比统计',
                        subtext: '公寓',
                        x:'center',
                        top: 50
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    series : [
                        {
                            name: '访问来源',
                            type: 'pie',
                            label:{
                                formatter: "{b}\n{d}%",
                                color:'#333'
                            },
                            radius : '50%',
                            center: ['48%', '55%'],
                            data: PieTwo_rtArr,
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                });
            },
            error:function (err) {
                // console.log('err---', err);
            }
        });
    }
    fetchPieTwo_rt();

    /**
     * 当前贷款订单列表 和 未来三天还款订单列表
     * */
    var vm = new Vue({
        el:'.containerView',
        data: function(){
            return {
                threeDaysRepaymentList: [],
                currentLoanList: [],
                isMax: false
            }
        },
        mounted: function(){
            this.getThreeDaysRepaymentList();
            this.getCurrentLoanList();
            this._InitScroll('A1','A2',60 * 5, 3000);
            this._InitScroll('B1','B2',60 * 5, 3000);
        },
        methods: {
            // 未来三天还款列表
            getThreeDaysRepaymentList: function () {
                $.ajax({
                    type: "POST",
                    url: path+"/financialRegulation/threeDaysRepaymentList.do",
                    dataType: "json",
                    success: function(result){
                        // console.log('res--未来三天还款列表---', result);
                        vm.threeDaysRepaymentList = result.data;
                    },
                    error:function (err) {
                        // console.log('err---', err);
                    }
                });
            },
            // 当前贷款订单列表
            getCurrentLoanList: function () {
                $.ajax({
                    type: "POST",
                    url: path+"/financialRegulation/currentLoanList.do",
                    dataType: "json",
                    success: function(result){
                        // console.log('res--当前贷款订单列表---', result);
                        vm.currentLoanList = result.data;
                    },
                    error:function (err) {
                        // console.log('err---', err);
                    }
                });
            },
            _InitScroll(_S1, _S2, _H, _T) {
                var marqueesHeight_S1 = _H ;
                var stopScroll_S1 = false;
                var scrollElem_S1 = document.getElementById(_S1);
                with(scrollElem_S1){
                    style.height = marqueesHeight_S1;
                    style.overflow = 'hidden';
                    style.backgroundColor = '#F5F5F5';
                    noWrap=true;
                }
                scrollElem_S1.onmouseover=new Function('stopScroll_S1=true');
                scrollElem_S1.onmouseout=new Function('stopScroll_S1=false');

                var preTop_S1 =0;
                var currentTop_S1 =0;
                var stopTime_S1 =0;

                var leftElem_S2 =document.getElementById(_S2);

                scrollElem_S1.appendChild(leftElem_S2.cloneNode(true));

                setTimeout(function (){
                    init_scrollText_S1();
                }, _T );

                function init_scrollText_S1 (){
                    scrollElem_S1.scrollTop=0;
                    setInterval(function (){
                        scrollUp_S1();
                    },50);
                }

                function scrollUp_S1 (){
                    if(stopScroll_S1 ){
                        return;
                    }
                    currentTop_S1 +=1;
                    if(currentTop_S1 ==(marqueesHeight_S1 +1)) {
                        stopTime_S1 +=1;
                        currentTop_S1 -=1;
                        if(stopTime_S1 == _T / 50 ) {
                            currentTop_S1 =0;
                            stopTime_S1 =0;
                        }
                    }else{
                        preTop_S1 =scrollElem_S1.scrollTop;
                        scrollElem_S1.scrollTop +=1;
                        if(preTop_S1 ==scrollElem_S1.scrollTop){
                            scrollElem_S1.scrollTop=0;
                            scrollElem_S1.scrollTop +=1;
                        }
                    }
                }
            }
        },
        filters: {
            /**
             *   30 待审核，17审批完成待放款，20已放款
             **/
            currentState: function(value) {
                var status = '';
                if (value === '30') {
                    status = '待审核';
                } else if (value === '17') {
                    status = '审批完成待放款';
                }else if(value === '20'){
                    status = '已放款';
                }else{
                    status = '订单状态非法';
                }
                return status;
            },
            /**
             *    0未还，3已还
             **/
            threeDayState: function(value) {
                var status = '';
                if (value === '0') {
                    status = '未还';
                } else if (value === '2') {
                    status = '已还';
                }else if (value === '3'){
                    status = '逾期';
                }else {
                    status = '未知';
                }
                return status;
            }
        }
    });

    // 处理全屏(需要全屏显示DOM元素)
    function launchFullScreen() {
        // 先检测最标准的方法
        if (document.documentElement.requestFullScreen) {
            document.documentElement.requestFullScreen();
        } else if (document.documentElement.mozRequestFullScreen) {
            // 其次，检测Mozilla的方法
            document.documentElement.mozRequestFullScreen();
        } else if (document.documentElement.webkitRequestFullScreen) {
            // if 检测 webkit的API
            document.documentElement.webkitRequestFullScreen();
        }//IE11
        else if (document.body.msRequestFullscreen) {
            document.body.msRequestFullscreen();
        }
    }

    // 退出全屏,不用管具体是哪个元素，因为屏幕是唯一的。
    function cancelFullscreen() {
        if (document.cancelFullScreen) {
            document.cancelFullScreen();
        } else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        } else if (document.webkitCancelFullScreen) {
            document.webkitCancelFullScreen();
        }else if (document.msExitFullscreen) {
            document.msExitFullscreen();
        }
    }


    $('#screen').click(function () {
        // console.log('----', $('#screen').hasClass('minScreen'));
        var isMax = $('#screen').hasClass('minScreen');
        if(!isMax){ // 非全屏-全屏
            launchFullScreen();
            $('#screen').removeClass('maxScreen').addClass('minScreen');
        }else{ // 全屏-非全屏
            cancelFullscreen();
            $('#screen').removeClass('minScreen').addClass('maxScreen');
        }
    });
};