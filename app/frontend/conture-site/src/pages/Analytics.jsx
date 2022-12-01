import SideBar from "../components/SideBar";
import "../html-css-template/css/Dashboard.css";
import "../html-css-template/css/Dashboard.css";
import ViewOne from "../components/AnalyticsViewOne";
import ViewTwo from "../components/AnalyticsViewTwo";
import ViewThree from "../components/AnalyticsViewThree";
import apiView from "../apiView";
import { useEffect, useState } from "react";

function Analytics() {

  const [BaseFurtos, setBaseFurtos] = useState([]);
  useEffect(() => {
    apiView.get("/vw_base_furtos?data=2").then((resposta) => {
      console.log(resposta.data);
      setBaseFurtos(resposta.data);
    });
  }, []);

  const [BaseFurtosQtProduct, setBaseFurtosQtProduct] = useState([]);
  useEffect(() => {
    apiView.get("/vw_furtos_por_qt_produtos?data=2").then((resposta) => {
      console.log(resposta.data);
      setBaseFurtosQtProduct(resposta.data);
    });
  }, []);

  const [BaseFurtosQtView, setBaseFurtosQtView] = useState([]);
  useEffect(() => {
    apiView.get("/vw_furtos_por_qt_visualizacao?data=2").then((resposta) => {
      console.log(resposta.data);
      setBaseFurtosQtView(resposta.data);
    });
  }, []);

  const [BaseFurtosQtUser, setBaseFurtosQtUser] = useState([]);
  useEffect(() => {
    apiView.get("/vw_furtos_por_qt_usuarios?data=2").then((resposta) => {
      console.log(resposta.data);
      setBaseFurtosQtUser(resposta.data);
    });
  }, []);

  return (
    <div id="dash">
      <SideBar />
      <section id="dash-pUm">
        <div className="title-bi">
          <h1>Analytics</h1>
        </div>
        <div className="dash-pDois">
          <ViewOne dados={BaseFurtos} dados2={BaseFurtosQtProduct}></ViewOne>
          <ViewTwo dados={BaseFurtos} dados2 ={BaseFurtosQtView}></ViewTwo>
          <ViewThree dados={BaseFurtos} dados2 ={BaseFurtosQtUser}></ViewThree>
        </div>
      </section>
    </div>
  );
}

export default Analytics;
