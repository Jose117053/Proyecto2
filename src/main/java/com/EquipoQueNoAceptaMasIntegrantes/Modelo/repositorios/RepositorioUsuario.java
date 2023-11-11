package com.EquipoQueNoAceptaMasIntegrantes.Modelo.repositorios;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** Implementación del Repositorio de usuarios.*/
public class RepositorioUsuario implements Repositorio<Usuario> {

    /* La única instancia de RepositorioUsuario. */
    private static volatile RepositorioUsuario uniqueInstance;
    /* La lista de usuarios. */
    private final List<Usuario> usuarios;

    /**
     * Obtiene la única instancia de InventarioUsuario.
     * @return La instancia única de InventarioUsuario.
     */
    public static RepositorioUsuario getInstance() {
        if (uniqueInstance == null) {
            synchronized (RepositorioUsuario.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new RepositorioUsuario();
                }
            }
        }
        return uniqueInstance;
    }

    /**
     * Constructor privado que inicializa la lista de usuarios.
     */
    private RepositorioUsuario() {
        usuarios = crearUsuarios();
    }

    /**
     * Crea la lista de usuarios.
     * @return lista con usuarios iniciales.
     */
    private List<Usuario> crearUsuarios() {
        List<Usuario> tempUsuarios;
        try {
            tempUsuarios = List.of(
                    new Usuario("luisa", "lu123", "Luisa Martinez"),
                    new Usuario("rafael", "raf123", "Rafael Martinez"),
                    new Usuario("samantha", "lu123", "Sam Martinez"));
        } catch (Exception e) {
            tempUsuarios = new ArrayList<>();
            System.err.println("Error inicializando usuarios: " + e.getMessage());
        }
        return tempUsuarios;
    }

    /** 
     * Método que devuelve la colección con todos los usuarios.
     * @return la colección de todos los usuarios.
     */
    @Override
    public Collection<Usuario> findAll() {
        return usuarios;
    }

    /**
     * Método que busca y regresa el primer usuario con cierto identificador.
     * @return el usuario con tal id.
     */
    @Override
    public Usuario find(Long PK) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(PK))
                .findFirst()
                .orElse(null);
    }

    /**
     * Busca un usuario por su nombre de usuario.
     * @param username nombre de usuario para buscar.
     * @return usuario con el nombre de usuario dado, o null si no se encuentra.
     */
    public Usuario buscarPorUser(String username) {
        return usuarios.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
