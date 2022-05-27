import { BrowserRouter, Route, Routes } from "react-router-dom";
import CadastrarPt1 from "./pages/CadastrarPt1";
import EsqueciSenha from "./pages/EsqueciSenha";
import Login from "./pages/Login";
import CadastroPt2 from "./pages/CadastroPt2";

function Rotas() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/login" element={<Login />} />
                <Route path="/esqueci-senha" element={<EsqueciSenha />} />
                <Route path="/cadastro" element={<CadastrarPt1 />} />
                <Route path="/finalizar-cadastro" element={<CadastroPt2/>} />
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;