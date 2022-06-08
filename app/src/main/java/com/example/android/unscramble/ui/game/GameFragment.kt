package com.example.android.unscramble.ui.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.unscramble.R
import com.example.android.unscramble.databinding.GameFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()




    private lateinit var binding: GameFragmentBinding
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = GameFragmentBinding.inflate(inflater, container, false)
        Log.d("GameFragment", "GameFragment created/re-created!")
        Log.d("GameFragment", "Word: ${viewModel.currentScrambledWord} " +
                "Score: ${viewModel.score} WordCount: ${viewModel.currentWordCount}")
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("GameFragment", "GameFragment destroyed!")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submit.setOnClickListener { onSubmitWord() }
        binding.skip.setOnClickListener { onSkipWord() }

//        updateNextWordOnScreen()
//        binding.score.text = getString(R.string.score, 0)
//        binding.wordCount.text = getString(
//                R.string.word_count, 0, MAX_NO_OF_WORDS)
        viewModel.score.observe(viewLifecycleOwner,
            { newScore ->
                binding.score.text = getString(R.string.score, newScore)
            })
        viewModel.currentScrambledWord.observe(viewLifecycleOwner, { newWord ->
            binding.textViewUnscrambledWord.text = newWord
        })
        viewModel.currentWordCount.observe(viewLifecycleOwner,
            {newWordCount ->
                binding.wordCount.text =
                    getString(R.string.word_count, newWordCount, MAX_NO_OF_WORDS)
            })
    }


    private fun onSubmitWord() {
//        if (viewModel.isUserWordCorrect(playerWord)){
//            setErrorTextField(false)
//            if (viewModel.nextWord()){
////                updateNextWordOnScreen()
//            }else{
//                showFinalScoreDialog()
//            }
//        } else{
//            setErrorTextField(true)
//        }
//        if (viewModel.nextWord()){
////            updateNextWordOnScreen()
//        }else{
//            showFinalScoreDialog()
//        }
        val playerWord = binding.textInputEditText.text.toString()
        if (viewModel.isUserWordCorrect(playerWord)) {
            setErrorTextField(false)
            if (!viewModel.nextWord()) {
                showFinalScoreDialog()
            }
        } else {
            setErrorTextField(true)
        }
    }

    private fun showFinalScoreDialog(){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.congratulations))
            .setMessage(getString(R.string.you_scored, viewModel.score.value))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.exit)){_,_ ->
                exitGame()
            }
            .setPositiveButton(getString(R.string.play_again)){_,_->
                restartGame()
            }.show()
    }

    private fun onSkipWord() {
        if (viewModel.nextWord()){
            setErrorTextField(false)
//            updateNextWordOnScreen()
        } else {
            showFinalScoreDialog()
        }
    }


//    private fun getNextScrambledWord(): String {
//        val tempWord = allWordsList.random().toCharArray()
//        tempWord.shuffle()
//        return String(tempWord)
//    }


    private fun restartGame() {
        viewModel.reinitializeData()
        setErrorTextField(false)
//        updateNextWordOnScreen()
    }


    private fun exitGame() {
        activity?.finish()
    }

    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding.textField.isErrorEnabled = true
            binding.textField.error = getString(R.string.try_again)
        } else {
            binding.textField.isErrorEnabled = false
            binding.textInputEditText.text = null
        }
    }


//    private fun updateNextWordOnScreen() {
//        binding.textViewUnscrambledWord.text = viewModel.currentScrambledWord
//    }
}
