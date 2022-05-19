package com.example.newsdetails

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.newsdetails.databinding.FragmentArticleBinding
import com.example.core.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_article.*

@AndroidEntryPoint
class ArticleNewsFragment : BaseFragment<FragmentArticleBinding>(
    FragmentArticleBinding::inflate
) {

    private val viewModel by viewModels<ArticleNewsViewModel>()
    val args: ArticleNewsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article
        webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { loadUrl(it) }
        }

        fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, getString(R.string.article_save_success_message), Snackbar.LENGTH_SHORT).show()
        }
    }
}