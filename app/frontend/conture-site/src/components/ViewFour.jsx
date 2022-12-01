import { Chart } from "react-google-charts";
// export const data = [
//     ["Ano", "Não uso mais", "Sem doações", "Pouco intuitivo"],
//     ["2019", 543, 400, 200],
//     ["2020", 700, 460, 250],
//     ["2021", 660, 1120, 300],
//     ["2022", 1030, 540, 350],
//   ];
  
  export const options = {
    chart: {
      title: "Qtd. de Reportes X Tipo de Reporte"
    },
    colors: ["#875B9B", "#979BFA", "#734AE7"],  
    // legend: 'none'
  };

function chartBarViewFour(props){

  const dataLine = [["Ano", "Assédio", "Discurso de ódio", "Informações do perfil", "Nudez ou atividade sexual", "Produto falso", "Spam"]]

  for (let idx = 0; idx < props.dados.length; idx++) {
    console.log("ViewFour",props.dados)
    dataLine.push([props.dados[idx].data, props.dados[idx].assedio, props.dados[idx].odio, props.dados[idx].perfil, props.dados[idx].nudez, props.dados[idx].produtoFalso, props.dados[idx].spam])
  }

  return (
    <Chart
      chartType="Bar"
      data={dataLine}
      options={options}
      width={"100%"}
      height={"100%"}
      backgroundColor="red"
    />
  )
}

export default chartBarViewFour