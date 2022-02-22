package com.recitrack.recitrackrecoleccion.Login;

import android.content.Context;

import com.recitrack.recitrackrecoleccion.Metodos;


public class LoginPresenter implements Login.Presenter{
    private  Login.View view;
    private Login.Model model;
    private Context context;

    Metodos metodos;
    public LoginPresenter(Login.View view,Context context){
         this.view=view;
         this.context=context;
         model= new LoginInteractor(this,context);
        metodos=new Metodos(context);
     }




    @Override
    public void Validar(String mail,String pass) {
        if (view!=null){
            model.Validar(mail,pass);
        }
    }


    @Override
    public void LoginOk() {
        if (view!=null){
            view.LoginOK();
        }
    }

    @Override
    public void Error(String msn) {
        view.Error(msn);
    }

    @Override
    public void ValidarLogin() {
        if(metodos.ValidarLogin()){
            view.LoginOK();
        }
    }

}
