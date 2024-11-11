//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.hasnain.application.Interfaces.NewsApiService
//import com.hasnain.application.R
//import com.hasnain.application.adapters.NewsAdapter
//import com.hasnain.application.models.NewsViewModelFactory
//import com.hasnain.application.repository.NewsRepository
//import com.hasnain.application.viewmodel.NewsViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//
//class NewsActivity : AppCompatActivity() {
//
//    private lateinit var viewModel: NewsViewModel
//    private lateinit var newsAdapter: NewsAdapter
//    private lateinit var recyclerView: RecyclerView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_news)
//
//        recyclerView = findViewById(R.id.recyclerView)
//
//        setupRecyclerView()
//
//        // Initialize ViewModel
//        val repository = NewsRepository(NewsApiService.RetrofiInstance)
//        viewModel = ViewModelProvider(this, NewsViewModelFactory(repository)).get(NewsViewModel::class.java)
//
//        // Observe news articles
//        viewModel.newsArticles.observe(this, { articles ->
//            articles?.let {
//                newsAdapter.updateData(it)
//            }
//        })
//
//        // Fetch news articles
//        viewModel.fetchNews()
//    }
//
//    private fun setupRecyclerView() {
//        newsAdapter = NewsAdapter(emptyList())
//        recyclerView.apply {
//            adapter = newsAdapter
//            layoutManager = LinearLayoutManager(this@NewsActivity)
//        }
//    }
//}
