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

    fun cargarPosts() {
        viewModelScope.launch {
            _loading.value = true
            try {
                _posts.value = repo.getPosts()
            } finally {
                _loading.value = false
            }
        }
    }
}
