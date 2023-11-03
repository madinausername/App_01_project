
package com.example.app_01_project.enter

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app_01_project.R
import com.example.app_01_project.api.APIClient
import com.example.app_01_project.api.APIService
import com.example.app_01_project.databinding.FragmentLoginBinding
import com.example.app_01_project.fragments.FirstPageFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding:FragmentLoginBinding

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
        binding= FragmentLoginBinding.inflate(inflater,container,false)
        binding.registrationChooseId.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_layout,RegistrationFragment())
                .commit()
        }
        val api =APIClient.getInstance().create(APIService::class.java)
        binding.loginBtnId.setOnClickListener {
            var username_layout_item=binding.usernameEdittextId.text
            var password_layout_item=binding.passwordEdittextId.text
            val l=Login(username_layout_item.toString(),password_layout_item.toString())
            api.login(l).enqueue(object : Callback<f>{
                override fun onResponse(call: Call<f>, response: Response<f>) {
                    if(response.isSuccessful && response.body()!=null){
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.main_layout,FirstPageFragment())
                            .commit()
                        val shared = MyShared.getInstance(requireContext())
                        val user = response.body()!!
                        shared.setUser(user)
                    }
                }
                override fun onFailure(call: Call<f>, t: Throwable) {
                    Log.d(ContentValues.TAG, "onFailure: $t")
                }
            })
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}