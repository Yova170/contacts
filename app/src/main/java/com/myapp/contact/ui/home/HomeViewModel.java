package com.myapp.contact.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    private List<Contacto> contactos;

    public HomeViewModel() {
        contactos = leerContactosXML(); // Lee los contactos del XML
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    // Agrega métodos para actualizar o agregar contactos (implementación omitida por brevedad)
}