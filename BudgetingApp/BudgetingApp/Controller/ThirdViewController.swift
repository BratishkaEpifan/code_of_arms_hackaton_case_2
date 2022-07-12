//
//  ThirdViewController.swift
//  BudgetingApp
//
//  Created by Olzhas Seiilkhanov on 09.07.2022.
//

import UIKit

class ThirdViewController: UIViewController {
	
	let collectionView = BACollectionView()
	let bonusInfoButton: UIButton = {
		let button = UIButton(type: .system)
		button.setTitle("Про гарантированные бонусы не забудьте       >", for: .normal)
		button.tintColor = .systemGreen
		button.addTarget(self, action: #selector(showBonusViewController), for: .touchUpInside)
		button.translatesAutoresizingMaskIntoConstraints = false
		return button
	}()
	
	@objc func showBonusViewController() {
		let vc = UIViewController()
		let imageView = UIImageView(image: UIImage(named: "bonusVC")!)
		vc.view = imageView
		vc.modalPresentationStyle = .pageSheet
		present(vc, animated: true)
	}
	
	override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .systemBackground
		configure()
    }
	
	func configure() {
		
		view.addSubview(collectionView)
		NSLayoutConstraint.activate([
			collectionView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
			collectionView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
			collectionView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
			collectionView.heightAnchor.constraint(equalToConstant: 150)
		])
		
		bonusInfoButton
		view.addSubview(bonusInfoButton)
		NSLayoutConstraint.activate([
			bonusInfoButton.topAnchor.constraint(equalTo: collectionView.bottomAnchor, constant: 5),
			bonusInfoButton.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 10),
			bonusInfoButton.trailingAnchor.constraint(equalTo: view.trailingAnchor),
			bonusInfoButton.heightAnchor.constraint(equalToConstant: 15)
		])
	}

	
}
