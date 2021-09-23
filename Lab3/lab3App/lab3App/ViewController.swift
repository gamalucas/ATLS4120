//
//  ViewController.swift
//  lab3App
//
//  Created by LUCAS GAMA on 9/21/21.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var mainImage: UIImageView!
    @IBOutlet weak var imageControl: UISegmentedControl!
    @IBOutlet weak var imageLabel: UILabel!
    @IBOutlet weak var fontSizeLabel: UILabel!
    @IBOutlet weak var capBt: UISwitch!
    @IBOutlet weak var capsLabel: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    func imageUpdate(){
        if imageControl.selectedSegmentIndex == 0{
            imageLabel.text = "Blue Mountains"
            imageLabel.textColor = UIColor.black
            capsLabel.textColor = UIColor.black
            fontSizeLabel.textColor = UIColor.black
            mainImage.image = UIImage(named: "blueMountain")
        }
        else if imageControl.selectedSegmentIndex == 1{
            imageLabel.text = "Red Mountains"
            imageLabel.textColor = UIColor.white
            capsLabel.textColor = UIColor.white
            fontSizeLabel.textColor = UIColor.white
            mainImage.image = UIImage(named: "redMountain")
        }
    }
    
    func updateCaps() {
        if capBt.isOn {
            imageLabel.text = imageLabel.text?.uppercased()
        }
        else {
            imageLabel.text = imageLabel.text?.lowercased()
        }
    }
    

    @IBAction func changeImage(_ sender: UISegmentedControl) {
        imageUpdate()
        updateCaps()
    }
    
    @IBAction func changeFont(_ sender: UISlider) {
        let fontSize = sender.value
        fontSizeLabel.text = String(format: "%.0f", fontSize)
        let CGFloatConverter = CGFloat(fontSize)
        imageLabel.font = UIFont.systemFont(ofSize: CGFloatConverter)
    }
    
    @IBAction func capBtAction(_ sender: UISwitch) {
        updateCaps()
    }
    
}

