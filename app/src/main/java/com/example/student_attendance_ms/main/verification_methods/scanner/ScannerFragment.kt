package com.example.student_attendance_ms.main.verification_methods.scanner

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.databinding.FragmentScannerBinding

private const val CAMERA_REQUEST_CODE = 10
internal val CAMERA_PERMISSION  = arrayOf(Manifest.permission.CAMERA)

class ScannerFragment : Fragment() {

    private lateinit var barcodeScanner: CodeScanner

    private var _binding: FragmentScannerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentScannerBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (context?.let { ContextCompat.checkSelfPermission(it, CAMERA_PERMISSION.toString()) } == PackageManager.PERMISSION_DENIED){
            requestPermissions(CAMERA_PERMISSION, CAMERA_REQUEST_CODE)
        } else {
            startScanning()
        }
    }

    private fun startScanning(){
        val activity = requireActivity()
        barcodeScanner = CodeScanner(activity, binding.scannerView)


        barcodeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                Toast.makeText(activity, it.text, Toast.LENGTH_LONG).show()

//                UserApiService.retrofitService.sendQR(
//                    it.text, intent?.authentication_token
//                ).enqueue(object : Callback<ResponseBody>{
//                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
//                    }
//
//                    // удачное сканирование qr
//                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                        Toast.makeText(context, "Удачное сканирование", Toast.LENGTH_LONG).show()
//                    }
//
//                })
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
        if (requestCode == CAMERA_REQUEST_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startScanning()
            } else {
                Toast.makeText(context, "Camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::barcodeScanner.isInitialized){
            barcodeScanner.startPreview()
        }
    }

    override fun onPause() {
        if (::barcodeScanner.isInitialized){
            barcodeScanner.releaseResources()
        }
        super.onPause()
    }
}
