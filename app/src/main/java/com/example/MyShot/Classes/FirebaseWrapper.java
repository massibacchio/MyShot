package com.example.MyShot.Classes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FirebaseWrapper {
    public static class Callback {
        private final static String TAG = Callback.class.getCanonicalName();
        private final Method method;
        private final Object thiz;

        public Callback(Method method, Object thiz) {
            this.method = method;
            this.thiz = thiz;
        }

        //la callback specifica quale metodo eseguire quando la richiesta firebase termina
        //usa la java reflection
        public static Callback newInstance(Object thiz, String name, Class<?>... prms) {
            Class<?> clazz = thiz.getClass();
            try {
                return new Callback(clazz.getMethod(name, prms), thiz);
            } catch (NoSuchMethodException e) {
                Log.w(TAG, "Cannot find method " + name + " in class " + clazz.getCanonicalName());

                // TODO: Better handling of the error
                throw new RuntimeException(e);
            }
        }
        public void invoke(Object... objs) {
            try {
                this.method.invoke(thiz, objs);
            } catch (IllegalAccessException | InvocationTargetException e) {
                Log.w(TAG, "Something went wrong during the callback. Message: " + e.getMessage());

                // TODO: Better handling of such an error
                throw new RuntimeException(e);
            }
        }
    }



    //define methods for login & registration

    public static class Auth{

        private static final String TAG =Auth.class.getCanonicalName();
        private final FirebaseAuth auth;

        //costruttore
        public Auth(){
            this.auth= FirebaseAuth.getInstance();
        }


        //check if user is authenticated
        public boolean isAuthenticated() {
            return this.auth.getCurrentUser() != null;
        }

        public FirebaseUser getUser() {
            return this.auth.getCurrentUser();
        }


        // metodi per signin e signup che accettano la callback
        // cioè il codice eseguito quando termina la chiamata di rete
        public void signIn (String email, String password, FirebaseWrapper.Callback callback){
            this.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        callback.invoke(task.isSuccessful());
                    }
                });
        }

        public void signUp (String email, String password, FirebaseWrapper.Callback callback){
            this.auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            callback.invoke(task.isSuccessful());
                        }
                    });
        }

        public void signOut (){}
    }
}
