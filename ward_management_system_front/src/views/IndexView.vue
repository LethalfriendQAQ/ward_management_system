<template>
  <div id="main" style="width: 600px; height: 400px;"></div>
</template>

<script setup>
import { onMounted } from 'vue';
import * as echarts from 'echarts';

// 初始化 ECharts 实例
let myChart;

onMounted(() => {
  const chartDom = document.getElementById('main');
  myChart = echarts.init(chartDom);

  const option = {
    title: {
      text: '科室患者分布',
      subtext: '实时更新',
      left: 'center'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      top: 'bottom'
    },
    toolbox: {
      show: true,
      feature: {
        mark: { show: true },
        dataView: { show: true, readOnly: false },
        restore: { show: true },
        saveAsImage: { show: true }
      }
    },
    series: [
      {
        name: '患者数量',
        type: 'pie',
        radius: '50%',
        center: ['50%', '50%'],
        data: [],
        itemStyle: {
          borderRadius: 8
        }
      }
    ]
  };

  myChart.setOption(option);

  function fetchPatientData() {
    fetch('/api/patientCountByDepartment')
      .then(response => response.json())
      .then(data => {
        const departmentData = data.map(item => ({
          value: item.count,
          name: item.departmentName
        }));
        myChart.setOption({
          series: [{
            data: departmentData
          }]
        });
      })
      .catch(error => console.error('Error fetching data:', error));
  }

  setInterval(fetchPatientData, 60000); // 每分钟更新一次
});
</script>

<style scoped>
/* 样式 */
</style>
