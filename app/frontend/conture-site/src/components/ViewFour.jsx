import { Chart } from "react-google-charts";
export const data = [
    ["Ano", "Não uso mais", "Sem doações", "Pouco intuitivo"],
    ["2019", 543, 400, 200],
    ["2020", 700, 460, 250],
    ["2021", 660, 1120, 300],
    ["2022", 1030, 540, 350],
  ];
  
  export const options = {
    chart: {
      title: "Qtd. de Desligamentos X Motivo de Desligamento"
    },
    colors: ["#875B9B", "#979BFA", "#734AE7"],  
    // legend: 'none'
  };

const chartBarViewFour = () => {
  return (
    <Chart
      chartType="Bar"
      data={data}
      options={options}
      width={"100%"}
      height={"100%"}
      backgroundColor="red"
    />
  )
}

export default chartBarViewFour