import React from "react";
import { Chart } from "react-google-charts";

export const options = {
  title: "Quantidade de Furtos vs. Quantidade de usuários na plataforma",
  hAxis: { title: "Furtos", minValue: 0, maxValue: 4000},
  vAxis: { title: "Usuários na plataforma", minValue: 0},
  legend: "TESTE",
  trendlines: {
    0: {
      type: "exponential",
      visibleInLegend: true,
    },
  },
};

function AnalyticsViewThree(props) {
  const dataView = [["Furtos", "Usuários"]];
  console.log(props.dados);
  console.log(props.dados2);
  for (var idx = 0; idx < props.dados2.length; idx++) {
    dataView.push([props.dados[idx].qtFurtos, props.dados2[idx].qtUsuarios]);
  } 

  return (
    <Chart
      chartType="ScatterChart"
      width="80%"
      height="400px"
      data={dataView}
      options={options}
    />
  );
}

export default AnalyticsViewThree;
