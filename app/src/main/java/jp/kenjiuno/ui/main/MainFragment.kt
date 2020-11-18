package jp.kenjiuno.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView
import jp.kenjiuno.R
import jp.kenjiuno.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<MainFragmentBinding>(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playerView = view.findViewById<StyledPlayerView>(R.id.player)

        viewModel.urlToBePlayed.observe(
            viewLifecycleOwner,
            Observer {
                it?.also { urlOf ->
                    val player = SimpleExoPlayer.Builder(requireContext()).build()
                    playerView.player = player

                    val mediaItem: MediaItem = MediaItem.fromUri(urlOf)
                    player.setMediaItem(mediaItem)
                    player.prepare()
                    player.play()
                }
            }
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}