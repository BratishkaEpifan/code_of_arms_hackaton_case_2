//
//  ThirdViewController.swift
//  BudgetingApp
//
//  Created by Olzhas Seiilkhanov on 09.07.2022.
//

import UIKit

class ThirdViewController: UIViewController {
	
	let recTitleLabel: UILabel = {
		let label = UILabel()
		label.text = "Рекомендация по категориям"
		label.textAlignment = .center
		label.font = .systemFont(ofSize: 24)
		label.translatesAutoresizingMaskIntoConstraints = false
		return label
	}()
	
	let recTitleView = BATopThirdView()
	
	let myCategoryTitleLabel: UILabel = {
		let label = UILabel()
		label.text = "Мои категорий"
		label.textAlignment = .center
		label.font = .systemFont(ofSize: 24)
		label.translatesAutoresizingMaskIntoConstraints = false
		return label
	}()
	
	let collectionView = BACollectionView()
	
	let otherCategoryTitleLabel: UILabel = {
		let label = UILabel()
		label.text = "Остальные категорий"
		label.textAlignment = .center
		label.font = .systemFont(ofSize: 24)
		label.translatesAutoresizingMaskIntoConstraints = false
		return label
	}()
	
	let otherCollectionView = BAOtherCollectionView()
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
		view.backgroundColor = .systemGray6
		configure()
    }
	
	func configure() {
		view.addSubview(recTitleLabel)
		NSLayoutConstraint.activate([
			recTitleLabel.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
			recTitleLabel.leadingAnchor.constraint(equalTo: view.leadingAnchor),
			recTitleLabel.trailingAnchor.constraint(equalTo: view.trailingAnchor),
			recTitleLabel.heightAnchor.constraint(equalToConstant: 30)
		])
		
		view.addSubview(recTitleView)
		NSLayoutConstraint.activate([
			recTitleView.topAnchor.constraint(equalTo: recTitleLabel.bottomAnchor),
			recTitleView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 10),
			recTitleView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -10),
			recTitleView.heightAnchor.constraint(equalToConstant: 100)
		])
		
		view.addSubview(myCategoryTitleLabel)
		NSLayoutConstraint.activate([
			myCategoryTitleLabel.topAnchor.constraint(equalTo: recTitleView.bottomAnchor, constant: 5),
			myCategoryTitleLabel.leadingAnchor.constraint(equalTo: view.leadingAnchor),
			myCategoryTitleLabel.trailingAnchor.constraint(equalTo: view.trailingAnchor),
			myCategoryTitleLabel.heightAnchor.constraint(equalToConstant: 30)
		])

		
		view.addSubview(collectionView)
		NSLayoutConstraint.activate([
			collectionView.topAnchor.constraint(equalTo: myCategoryTitleLabel.bottomAnchor),
			collectionView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
			collectionView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
			collectionView.heightAnchor.constraint(equalToConstant: 150)
		])
		
		view.addSubview(bonusInfoButton)
		NSLayoutConstraint.activate([
			bonusInfoButton.topAnchor.constraint(equalTo: collectionView.bottomAnchor, constant: 5),
			bonusInfoButton.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 10),
			bonusInfoButton.trailingAnchor.constraint(equalTo: view.trailingAnchor),
			bonusInfoButton.heightAnchor.constraint(equalToConstant: 15)
		])
		
		view.addSubview(otherCategoryTitleLabel)
		NSLayoutConstraint.activate([
			otherCategoryTitleLabel.topAnchor.constraint(equalTo: bonusInfoButton.bottomAnchor),
			otherCategoryTitleLabel.leadingAnchor.constraint(equalTo: view.leadingAnchor),
			otherCategoryTitleLabel.trailingAnchor.constraint(equalTo: view.trailingAnchor),
			otherCategoryTitleLabel.heightAnchor.constraint(equalToConstant: 30)
		])

		
		view.addSubview(otherCollectionView)
		NSLayoutConstraint.activate([
			otherCollectionView.topAnchor.constraint(equalTo: otherCategoryTitleLabel.bottomAnchor),
			otherCollectionView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
			otherCollectionView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
			otherCollectionView.bottomAnchor.constraint(equalTo: view.bottomAnchor, constant: -30)
		])
	}

	
}
