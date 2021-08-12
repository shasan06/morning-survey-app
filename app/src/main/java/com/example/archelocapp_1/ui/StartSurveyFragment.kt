package com.example.archelocapp_1.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.archelocapp_1.R
import com.example.archelocapp_1.databinding.ActivityStartSurveyBinding
import com.example.archelocapp_1.viewmodels.SuspectedNestViewModel

class StartSurveyFragment : Fragment(){

    private lateinit var binding: ActivityStartSurveyBinding
    private val suspectedNestViewModel: SuspectedNestViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityStartSurveyBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        init()

        listeners()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun listeners() {
        binding.previousButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.nextButton.setOnClickListener {
            findNavController().navigate(StartSurveyFragmentDirections.actionStartSurveyFragmentToObserverFragment())
        }
        //On clicking the plus button the data to be submitted in the add morning survey method of the
        //suspectedNestViewModelclass
//        binding.plusButton.setOnClickListener {
//            // to bind the data of the calender
//            val cal = binding.calendarView2.date.toString()
//            //to bind the time
//            val time = binding.timePicker1.hour.toString() +" : "+ binding.timePicker1.minute.toString()
//
//            val spinnerdata1 = binding.spinner.selectedItem.toString()
//            val spinnerdata2 = binding.spinner2.selectedItem.toString()
//            suspectedNestViewModel.addMorningSurvey(cal, time, spinnerdata1, spinnerdata2)
//            //the timer mode
//
//        }
    }

    private fun init() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinnerItems,
            android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                //binding.spinner.adapter = adapter
            }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinnerItems1,
            android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                //binding.spinner2.adapter = adapter
            }
    }












}