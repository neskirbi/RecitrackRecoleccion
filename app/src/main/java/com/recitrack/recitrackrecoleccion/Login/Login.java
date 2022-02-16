package com.recitrack.recitrackrecoleccion.Login;


public interface Login {

    interface View{
        void LoginOK();
        void Error(String msn);
    }

    interface Presenter{
        void Validar(String mail,String pass);
        void LoginOk();
        void Error(String msn);

        void ValidarLogin();
    }

    interface Model{
        void Validar(String mail,String pass);
    }
}
