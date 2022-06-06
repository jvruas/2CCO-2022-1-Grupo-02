import { BrowserRouter, Route, Routes } from "react-router-dom";
import EsqueciSenha from "./pages/EsqueciSenha";
import Login from "./pages/Login";
import Cadastro from "./pages/Cadastro";
import PopUp from "./pages/PopUp";

function Rotas() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="*" element={<Login/>} />
                <Route path="/email-esqueci-senha" element={<PopUp/>} />
                <Route path="/esqueci-senha" element={<EsqueciSenha/>} />
                <Route path="/cadastro" element={<Cadastro/>} />
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;