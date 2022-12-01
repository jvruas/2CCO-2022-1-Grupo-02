import SideBar from "../components/SideBar";
import "../html-css-template/css/Dashboard.css";
import ChartLine from "../components/ChartLine";
import ChartBarViewTwo from "../components/ViewTwo";
import ChartBarViewThree from "../components/ViewThree";
import ChartBarViewFour from "../components/ViewFour";
import MapBrazil from "../components/MapBrazil";
import MapBrazilDonations from "../components/MapBrazilDonations";
import apiView from "../apiView";
import { useEffect, useState } from "react";

function Dashboard() {
  const [mapReport, setMapReport] = useState([]);
  useEffect(() => {
    apiView.get("/vw_qtd_reportes_estado?data=2").then((resposta) => {
      console.log(resposta.data);
      setMapReport(resposta.data);
    });
  }, []);

  const [mapDonations, setMapDonations] = useState([]);
  useEffect(() => {
    apiView.get("/vw_qtd_doacao_estado?data=2").then((resposta) => {
      console.log(resposta.data);
      setMapDonations(resposta.data);
    });
  }, []);

  const [historyDonations, setHistoryDonations] = useState([]);
  useEffect(() => {
    apiView.get("/vw_doacao_historica?data=2").then((resposta) => {
      console.log(resposta.data);
      setHistoryDonations(resposta.data);
    });
  }, []);

  const [qtdDesligamentosMotivo, setQtdDesligamentosMotivo] = useState([]);
  useEffect(() => {
    apiView
      .get("/vw_qtd_desligamento_motivo_desligamento?data=2")
      .then((resposta) => {
        console.log(resposta.data);
        setQtdDesligamentosMotivo(resposta.data);
      });
  }, []);

  const [qtdDoadosVisu, setQtdDoadosVisu] = useState([]);
  useEffect(() => {
    apiView.get("/vw_produtos_vendidos_doados?data=2").then((resposta) => {
      console.log(resposta.data);
      setQtdDoadosVisu(resposta.data);
    });
  }, []);

  const [qtdReportes, setQtdReportes] = useState([]);
  useEffect(() => {
    apiView.get("/vw_reporte_tipo_reporte?data=2").then((resposta) => {
      console.log(resposta.data);
      setQtdReportes(resposta.data);
    });
  }, []);

  return (
    <div id="dash">
      <SideBar />
      <section id="dash-pUm">
        <div className="title-bi">
          <h1>GRÁFICOS BI - VISÃO GERAL</h1>
        </div>
        <div className="dash-pDois">
          {/* <div className="dash-scroll"> */}

          <div className="dashMaps">
            <div>
              <h1>Quantidade de reportes por Estado</h1>
              <MapBrazil mapa={mapReport}></MapBrazil>
            </div>

            <div>
              <h1>Quantidade de doações por Estado</h1>
              <MapBrazilDonations mapa={mapDonations}></MapBrazilDonations>
            </div>
          </div>

          <div>
            <ChartLine dados={historyDonations}></ChartLine>
          </div>

          <div>
            <ChartBarViewTwo dados={qtdDoadosVisu} />
          </div>

          <div>
            <ChartBarViewFour dados={qtdReportes} />
          </div>

          {/* </div> */}
        </div>
      </section>
    </div>
  );
}

export default Dashboard;
