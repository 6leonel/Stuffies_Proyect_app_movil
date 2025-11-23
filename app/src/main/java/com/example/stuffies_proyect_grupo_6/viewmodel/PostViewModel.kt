package com.example.stuffies_proyect_grupo_6.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stuffies_proyect_grupo_6.model.Post
import com.example.stuffies_proyect_grupo_6.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val repo: PostRepository = PostRepository()

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    // ⬇️ NUEVO: estado de error para mostrarlo en UI si falla la API
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun cargarPosts() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                _posts.value = repo.getPosts()
            } catch (e: Exception) {
                // Evita que la app se caiga si la API falla
                _posts.value = emptyList()
                _error.value = e.message ?: "Error al cargar productos desde el microservicio."
            } finally {
                _loading.value = false
            }
        }
    }
}
