package com.EquipoQueNoAceptaMasIntegrantes.Controladores.repositorios;

import com.EquipoQueNoAceptaMasIntegrantes.Modelo.objetos.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implementación del inventario de usuarios, utilizando el patrón Singleton.
 * Gestiona un conjunto de usuarios.
 * 
 */
public class RepositorioUsuario implements Repositorio<Usuario> {

    /* La unica instancia de Repositorio Usuario. */
    private static volatile RepositorioUsuario uniqueInstance;
    /* La lista de usuarios. */
    public final List<Usuario> usuarios;

    /**
     * Obtiene la única instancia de InventarioUsuario.
     * 
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

    // Constructor privado que inicializa la lista de usuarios.
    public RepositorioUsuario() {
        usuarios = crearUsuarios();
    }

    /**
     * Crea la lista de usuarios.
     * 
     * @return Lista con usuarios iniciales.
     */
    private List<Usuario> crearUsuarios() {
        List<Usuario> tempUsuarios;
        try {
            tempUsuarios = List.of(
                    new Usuario("carlos", "carlito23", "Carlos Martinez"),
                    new Usuario("maria", "mary55", "Maria Lopez"),
                    new Usuario("juan", "juan97", "Juan Ramirez"));
        } catch (Exception e) {
            tempUsuarios = new ArrayList<>();
            System.err.println("Error inicializando usuarios: " + e.getMessage());
        }
        return tempUsuarios;
    }

    /**
     * Método que devuelve la colección de todos los usuarios.
     */
    @Override
    public Collection<Usuario> findAll() {
        return usuarios;
    }

    /**
     * Método que regresa el usuario de la colección cuyo id es identico.
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
     * 
     * @param username Nombre de usuario para buscar.
     * @return Usuario con el nombre de usuario dado, o null si no se encuentra.
     */
    public Usuario buscarPorUser(String username) {
        return usuarios.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
