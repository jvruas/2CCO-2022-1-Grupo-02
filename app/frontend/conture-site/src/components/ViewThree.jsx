import { Chart } from "react-google-charts";
export const data = [
    ["Ano", "Spam", "Não entregou"],    
    ["2019", 400, 200],
    ["2020", 460, 250],
    ["2021", 1120, 300],
    ["2022", 540, 350],
  ];
  export const options = {
    chart: {
      title: "Qtd. de Reportes X Tipo de Reporte"
    },
    colors: ["#875B9B", "#979BFA", "#734AE7"],  
  };

const chartBarViewThree = () => {
  return (
    <Chart
      chartType="Bar"
      data={data}
      options={options}
      width={"100%"}
      height={"100%"}
    />
  )
}
export default chartBarViewThree