//
//  ViewController.swift
//  todaysArt
//
//  Created by LUCAS GAMA on 9/1/21.
//

import UIKit

class ViewController: UIViewController {
    
    @IBAction func newArtBt(_ sender: UIButton) {
        creditsLabel.text = ""
        var randomInt = Int.random(in: 1..<8)
        if randomInt == 1 {
            imgPreview.image = UIImage(named: "monalisa")
            imgLabel.text = "Monalisa, by Leonardo da Vinci - 1503 to 1519"
        }
        else if randomInt == 2 {
            imgPreview.image = UIImage(named: "TheGirlWithAPearlEarring")
            imgLabel.text = "Girl With a Pearl Earring by Johannes Vermeer - 1665"
        }
        else if randomInt == 3 {
            imgPreview.image = UIImage(named: "Guernica")
            imgLabel.text = "Guernica by Pablo Picasso - 1937"
        }
        else if randomInt == 4 {
            imgPreview.image = UIImage(named: "TheBirthofVenus")
            imgLabel.text = "The Birth of Venus by Sandro Botticelli - 1485"
        }
        else if randomInt == 5 {
            imgPreview.image = UIImage(named: "TheKiss")
            imgLabel.text = "The Kiss by Gustav Klimt - 1907 to 1908"
        }
        else if randomInt == 6 {
            imgPreview.image = UIImage(named: "TheScream")
            imgLabel.text = "The Scream by Edvard Munch - 1893"
        }
        else if randomInt == 7 {
            imgPreview.image = UIImage(named: "TheStarryNight")
            imgLabel.text = "The Starry Night by Vincent van Gogh - 1889"
        }
    }
    
    @IBAction func creditsBt(_ sender: UIButton) {
        imgPreview.image = nil
        imgLabel.text = ""
        creditsLabel.text = "Made by Lucas Gama for ATLS4120 Class on Fall 2021"
    }
    
    @IBOutlet weak var frameView: UIImageView!
    @IBOutlet weak var imgLabel: UILabel!
    @IBOutlet weak var imgPreview: UIImageView!
    @IBOutlet weak var creditsLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

