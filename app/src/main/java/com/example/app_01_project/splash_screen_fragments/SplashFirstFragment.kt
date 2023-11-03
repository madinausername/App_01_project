package com.example.app_01_project.splash_screen_fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app_01_project.R
import com.example.app_01_project.databinding.FragmentSplashFirstBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFirstFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var progressStatus = 0
    private var handler = Handler()
    private var limit=0
    private lateinit var binding: FragmentSplashFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSplashFirstBinding.inflate(inflater,container,false)
        //binding.button.setOnClickListener {
            Thread(Runnable {
                while (progressStatus < 100){
                    // update progress status
                    progressStatus +=1

                    // sleep the thread for 100 milliseconds
                    Thread.sleep(50)

                    // update the progress bar
                    handler.post {
                        binding.progressBar.progress = progressStatus
                        binding.pr.text = "$progressStatus"
                        limit=progressStatus
                    }
                }
                if(limit==100){
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.main_layout, SplashTwoFragment())
                        .commit()
                }
            }).start()

        //}

        return binding.root
    }

}