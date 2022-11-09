
import SideBar from "../components/SideBar";
import "../html-css-template/css/Dashboard.css";
import ChartLine from "../components/ChartLine";
import ChartBarViewTwo from "../components/ViewTwo";
import ChartBarViewThree from "../components/ViewThree";
import ChartBarViewFour from "../components/ViewFour";  
import MapBrazil from "../components/MapBrazil"
import apiView from "../apiView"
import { useEffect, useState } from "react";

function Dashboard() {

  const [map, setMap] = useState([]);
  useEffect(() => {
          apiView.get("/vw_qtd_reportes_estado?data=2").then((resposta) => {
              console.log(resposta.data);
              setMap(resposta.data);
          })
  }, [])

  return (
    <div id="dash">
      <SideBar />
      <section id="dash-pUm">
        <div className="title-bi">
          <h1>GRÁFICOS BI - VISÃO GERAL</h1>
        </div>
        <div className="dash-pDois">
          <div className="dash-scroll">
            <MapBrazil
              mapa={map}
            ></MapBrazil>
            <ChartLine></ChartLine>
            <ChartBarViewTwo />
            <ChartBarViewThree />
            <ChartBarViewFour />
          </div>
        </div>
      </section>
    </div>
  );
}

export default Dashboard;
