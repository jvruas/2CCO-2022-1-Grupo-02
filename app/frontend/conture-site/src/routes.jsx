import { BrowserRouter, Route, Routes } from "react-router-dom";
import EsqueciSenha from "./pages/EsqueciSenha";
import Login from "./pages/Login";
import Cadastro from "./pages/Cadastro";
import PopUp from "./pages/PopUp";
import Feed from "./pages/Feed";

function Rotas() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="*" element={<Login/>} />
                <Route path="/email-esqueci-senha" element={<PopUp/>} />
                <Route path="/esqueci-senha" element={<EsqueciSenha/>} />
                <Route path="/cadastro" element={<Cadastro/>} />
                <Route path="/feed" element={<Feed/>} />
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;