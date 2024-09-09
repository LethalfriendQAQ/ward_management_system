<template>
  <div class="chart-container">
    <div id="main"></div>
    <div id="bedChart"></div>
  </div>
</template>

<script setup>
import * as echarts from "echarts";
import { onMounted } from "vue";
import patientApi from "@/api/patientApi";
import bedApi from "@/api/bedApi";

function init() {
  patientApi.getPatientCountByDepartment()
    .then(resp => {

      if (Array.isArray(resp)) {
        let data = resp.map(item => ({
          value: item.patientCount,
          name: item.departmentName
        }));

        var myChart = echarts.init(document.getElementById('main'));

        var option = {
          title: {
            text: '各部门患者数量',
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
              animationType: 'scale',
              animationEasing: 'elasticOut',
              animationDelay: function (idx) {
                return Math.random() * 200;
              }
            }
          ]
        };

        myChart.setOption(option);
      } else {
        console.error("API response data is not an array");
      }
    })
    .catch(error => {
      console.error("API request error:", error);
    });
}

function initBedChart() {
  bedApi.getBedOccupancyByDepartment()
    .then(resp => {
      if (Array.isArray(resp)) {
        let departmentNames = resp.map(item => item.departmentId);
        let occupancyRates = resp.map(item => item.occupancyRate);

        var myChart = echarts.init(document.getElementById('bedChart'));

        var option = {
          title: {
            text: '各科室病床占有率',
            left: 'center',
            textStyle: {
              color: '#000000'
            }
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          xAxis: {
            type: 'category',
            data: departmentNames,
            axisLabel: {
              //rotate: 45  // 避免标签重叠
            }
          },
          yAxis: {
            type: 'value',
            axisLabel: {
              formatter: '{value} %'
            }
          },
          series: [
            {
              name: '占有率',
              type: 'bar',
              data: occupancyRates,
              label: {
                show: true,
                position: 'top',
                formatter: '{c} %'
              },
              itemStyle: {
                color: '#73C0DE'
              }
            }
          ]
        };

        myChart.setOption(option);
      } else {
        console.error("API response data is not an array");
      }
    })
    .catch(error => {
      console.error("API request error:", error);
    });
}

onMounted(() => {
  init();
  initBedChart();
});
</script>

<style scoped>
.chart-container {
  display: flex;
  justify-content: space-between; /* 确保图表在容器中有空间分隔 */
}

#main {
  width: 50%;   /* 设置宽度占容器的50% */
  height: 80vh; /* 设置图表的高度 */
}

#bedChart {
  width: 50%;   /* 设置宽度占容器的50% */
  height: 400px; /* 设置图表的高度 */
}
</style>
