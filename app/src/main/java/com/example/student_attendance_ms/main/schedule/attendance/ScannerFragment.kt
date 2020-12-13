package com.example.student_attendance_ms.main.schedule.attendance

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.databinding.FragmentScannerBinding
import com.example.student_attendance_ms.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException
import javax.inject.Inject

private const val CAMERA_REQUEST_CODE = 10
internal val CAMERA_PERMISSION = arrayOf(Manifest.permission.CAMERA)

@AndroidEntryPoint
class ScannerFragment : Fragment() {

    private lateinit var barcodeScanner: CodeScanner

    private var _binding: FragmentScannerBinding? = null
    private val binding get() = _binding!!

    // получаем переменную eventId из фрагмента EventDetailFragment
    private val args: ScannerFragmentArgs by navArgs()

    @Inject
    lateinit var scannerViewModelFactory: ScannerViewModel.AssistedFactory

    private val scannerViewModel: ScannerViewModel by viewModels {
        ScannerViewModel.provideFactory(
                scannerViewModelFactory,
                args.eventId
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentScannerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (context?.let { ContextCompat.checkSelfPermission(it, CAMERA_PERMISSION.toString()) } == PackageManager.PERMISSION_DENIED) {
            requestPermissions(CAMERA_PERMISSION, CAMERA_REQUEST_CODE)
        } else {
            startScanning()
        }
    }

    private fun startScanning() {
        val activity = requireActivity()
        barcodeScanner = CodeScanner(activity, binding.scannerView)

        barcodeScanner.decodeCallback = DecodeCallback {
            scannerViewModel.provideCodeSending(it.text)
        }

        scannerViewModel.scanResult.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> Toast.makeText(activity, it.data.message, Toast.LENGTH_SHORT).show()
                is DataState.Error -> {
                    when (it.exception) {
                        is HttpException -> Toast.makeText(
                                activity,
                                resources.getString(R.string.already_submit_code),
                                Toast.LENGTH_SHORT)
                                .show()
                        else -> Toast.makeText(activity, it.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        barcodeScanner.errorCallback = ErrorCallback {
            activity.runOnUiThread {
                Toast.makeText(activity, "Camera initialization error: ${it.message}",
                        Toast.LENGTH_LONG).show()
            }
        }

        binding.scannerView.setOnClickListener {
            barcodeScanner.startPreview()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startScanning()
            } else {
                Toast.makeText(context, "Camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::barcodeScanner.isInitialized) {
            barcodeScanner.startPreview()
        }
    }

    override fun onPause() {
        if (::barcodeScanner.isInitialized) {
            barcodeScanner.releaseResources()
        }
        super.onPause()
    }
}
