import { Chart } from "react-google-charts";

// export const data = [
//     ["Ano", "Spam", "Não entregou"],    
//     ["2019", 400, 200],
//     ["2020", 460, 250],
//     ["2021", 1120, 300],
//     ["2022", 540, 350],
//   ];
  export const options = {
    chart: {
      title: "Qtd. de Desligamentos X Motivo de Desligamento"
    },
    colors: ["#875B9B", "#979BFA", "#734AE7"],  
  };

function chartBarViewThree(props) {

  console.log(props.dados);
  const dataLine = [["Ano", "Problemas com o site", "Não acho o eletrônico", "Outros"]]

  for (let idx = 0; idx < props.dados.length; idx++) {
    console.log("ViewThree",props.dados)
    dataLine.push([props.dados[idx].data, props.dados[idx].problemasSite, props.dados[idx].naoAchoEletronico, props.dados[idx].outros])
  }
  
  
  return (
    <Chart
      chartType="Bar"
      data={dataLine}
      options={options}
      width={"100%"}
      height={"100%"}
    />
  )
}
export default chartBarViewThree