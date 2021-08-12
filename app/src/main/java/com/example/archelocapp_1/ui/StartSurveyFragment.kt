package com.example.archelocapp_1.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.archelocapp_1.R
import com.example.archelocapp_1.databinding.ActivityStartSurveyBinding
import com.example.archelocapp_1.viewmodels.MorningSurveyViewModel
import java.text.SimpleDateFormat
import java.util.*

//Morning survey fragment
class StartSurveyFragment : Fragment(){

    private lateinit var binding: ActivityStartSurveyBinding
    private val morningSurveyViewModel: MorningSurveyViewModel by viewModels()


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

        binding.submit.setOnClickListener {
            //1 Area field taking data from ui id areatext and storing into a variable area
            val area = binding.AreaText.text.toString()
            //Setingup Calendar to collect date selected from date Picker
            val day: Int = binding.datePicker.getDayOfMonth()
            val month: Int = binding.datePicker.getMonth()
            val year: Int = binding.datePicker.getYear()
            val calendar: Calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            val formatedDate: String = sdf.format(calendar.getTime())
            //2 Date
            val date = formatedDate
            //3 Beach
            val beach = binding.BeachText.text.toString()
            //4 sector
            val sector = binding.SectorText.text.toString()
            //5 subsector
            val subsector = binding.SubSectorText.text.toString()
            //6 emergence_event string
            val emergence_event = binding.EmergenceEventText.text.toString()
            //7 nest
            var nest: String = binding.NestText.text.toString()
            //8 distance_to_sea
            val distance_to_sea = binding.DistancetoSeaText.toString()
            //9 track_type
            val track_type = binding.TrackTypeText.text.toString()
            //10 non_nesting_attempts
            val non_nesting_attempts = binding.NonNestingAttemptsText.text.toString()
            //11 tags
            val tags = binding.tagsText.text.toString()
            //12 comments
            val comment = binding.CommentsText.text.toString()
            //13 photo_id
            val photo_id = binding.photoIDText.text.toString()
            // Get location from fusded location provider for survey location

//            viewModel.fusedLocationClient?.lastLocation
//                ?.addOnSuccessListener { location : Location? ->
//                    if (location != null) {
//                        viewModel.newSurvey.gps_latitude = location.latitude.toString()
//                        viewModel.newSurvey.gps_longitude = location.longitude.toString()
//                    }
//                }


            //val morningSurvey: MorningSurvey = MorningSurvey() can pass this object
            morningSurveyViewModel.submitData(area = area, dateData = date, beach = beach, sector = sector,
                subsector = subsector, emergence_event= emergence_event, nest = nest, distance_to_sea = distance_to_sea,
                track_type = track_type ,non_nesting_attempts = non_nesting_attempts, tags = tags,
                comment = comment, photo_id = photo_id)
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