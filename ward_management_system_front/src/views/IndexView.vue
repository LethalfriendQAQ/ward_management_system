<template>
  <div id="main"></div>
</template>

<script setup>
import * as echarts from "echarts";
import { onMounted } from "vue";
import patientApi from "@/api/patientApi";

function init() {
  patientApi.getPatientCountByDepartment()
    .then(resp => {
      console.log("API response:", resp);

      // 确保 resp 是一个数组
      if (Array.isArray(resp)) {
        // 处理数据
        let data = resp.map(item => ({
          value: item.patientCount,
          name: item.departmentName
        }));

        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
          title: {
            text: '各部门患者数量',
            // left: 'center',
            top: 20,
            textStyle: {
              color: '#000000'
            }
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)',
            backgroundColor: 'rgba(50, 50, 50, 0.7)',
            borderColor: '#333',
            borderWidth: 1,
            textStyle: {
              color: '#FFFFFF'
            }
          },
          legend: {
            data: data.map(item => item.name),
            right: '10%',
            itemWidth: 10,
            itemHeight: 10,
            textStyle: {
              color: '#000000'
            }
          },
          series: [
            {
              name: '人数',
              type: 'pie',
              radius: '55%',
              center: ['50%', '50%'],
              data: data.sort((a, b) => a.value - b.value),
              roseType: 'radius',
              label: {
                color: 'rgba(#000000, 0.3)'
              },
              labelLine: {
                lineStyle: {
                  color: 'rgba(#000000, 0.3)'
                },
                smooth: 0.2,
                length: 10,
                length2: 20
              },
              // itemStyle: {
              //   color: '#5694b6',
              //   shadowBlur: 200,
              //   shadowColor: 'rgba(0, 0, 0, 0.5)'
              // },
              animationType: 'scale',
              animationEasing: 'elasticOut',
              animationDelay: function (idx) {
                return Math.random() * 200;
              }
            }
          ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
      } else {
        console.error("API response data is not an array");
      }
    })
    .catch(error => {
      console.error("API request error:", error);
    });
}


// 生命周期函数，自动执行初始化
onMounted(() => {
  init();
});
</script>

<style scoped>
#main {
  width: 900px;
  height: 80vh;
}
</style>
