package com.csm2s.maquette

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.csm2s.maquette.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonYes.setOnClickListener {
            val answerQuestionByButton = view.findViewById<Button>(R.id.button_yes)
            val answerQuestion = answerQuestionByButton.text.toString()
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(answerQuestion)
            findNavController().navigate(action)
        }

        binding.buttonNo.setOnClickListener {
            val answerQuestionByButton = view.findViewById<Button>(R.id.button_no)
            val answerQuestion = answerQuestionByButton.text.toString()
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(answerQuestion)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}