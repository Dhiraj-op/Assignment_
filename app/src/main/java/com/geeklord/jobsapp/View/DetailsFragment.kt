package com.geeklord.jobsapp.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import com.geeklord.jobsapp.Models.JobDetails
import com.geeklord.jobsapp.Models.JobsEntity
import com.geeklord.jobsapp.databinding.FragmentDetailsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private var itemData: JobDetails? = null
    private var offlineitemData: JobsEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val source = arguments?.getString("source")
        if (source == null) {
            Snackbar.make(binding.root, "Source not specified", Snackbar.LENGTH_SHORT).show()
            return
        }

        when (source) {
            "JobsFragment" -> setJobData()
            "SavedJobsFragment" -> setOfflineJobData()
            else -> Snackbar.make(binding.root, "Invalid source", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setJobData() {
        val json = arguments?.getString("jobData")
        if (json.isNullOrEmpty()) {
            Snackbar.make(binding.root, "Job data is missing", Snackbar.LENGTH_SHORT).show()
            return
        }

        itemData = Gson().fromJson(json, JobDetails::class.java)

        if (itemData == null) {
            Snackbar.make(binding.root, "Failed to parse job data", Snackbar.LENGTH_SHORT).show()
            return
        }

        // Safe usage of itemData
        binding.tvPlace.text = "Place : ${itemData?.primaryDetails?.place ?: "N/A"}"
        binding.tvSalary.text = "Salary : ${itemData?.primaryDetails?.salary ?: "N/A"}"
        binding.tvJobType.text = "Job Type : ${itemData?.primaryDetails?.jobType ?: "N/A"}"
        binding.tvExperience.text = "Experience : ${itemData?.primaryDetails?.experience ?: "N/A"}"
        binding.tvQualification.text = "Qualification : ${itemData?.primaryDetails?.qualification ?: "N/A"}"
        binding.tvCompanyName.text = "Company : ${itemData?.companyName ?: "N/A"}"
        binding.tvJobRole.text = "Job Role : ${itemData?.jobRole ?: "N/A"}"
        binding.tvTitle.text = "Job Title : ${itemData?.title ?: "N/A"}"
    }

    private fun setOfflineJobData() {
        val json = arguments?.getString("savedJobData")
        if (json.isNullOrEmpty()) {
            Snackbar.make(binding.root, "Saved job data is missing", Snackbar.LENGTH_SHORT).show()
            return
        }

        offlineitemData = Gson().fromJson(json, JobsEntity::class.java)

        if (offlineitemData == null) {
            Snackbar.make(binding.root, "Failed to parse saved job data", Snackbar.LENGTH_SHORT).show()
            return
        }

        // Safe usage of offlineitemData
        binding.tvPlace.text = "Place : ${offlineitemData?.place ?: "N/A"}"
        binding.tvSalary.text = "Salary : ${offlineitemData?.salary ?: "N/A"}"
        binding.tvJobType.text = "Job Type : ${offlineitemData?.jobType ?: "N/A"}"
        binding.tvExperience.text = "Experience : ${offlineitemData?.experience ?: "N/A"}"
        binding.tvQualification.text = "Qualification : ${offlineitemData?.qualification ?: "N/A"}"
        binding.tvCompanyName.text = "Company : ${offlineitemData?.companyName ?: "N/A"}"
        binding.tvJobRole.text = "Job Role : ${offlineitemData?.jobRole ?: "N/A"}"
        binding.tvTitle.text = "Job Title : ${offlineitemData?.title ?: "N/A"}"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
