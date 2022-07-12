//
//  ThirdViewController.swift
//  BudgetingApp
//
//  Created by Olzhas Seiilkhanov on 09.07.2022.
//

import UIKit

class ThirdViewController: UIViewController, UICollectionViewDataSource, UICollectionViewDelegate {
	
	let collectionView: UICollectionView = {
		let layout = UICollectionViewFlowLayout()
		layout.sectionInset = UIEdgeInsets(top: 5, left: 5, bottom: 5, right: 5)
		layout.itemSize = CGSize(width: 100, height: 120)
		layout.scrollDirection = .horizontal
		let collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
		collectionView.translatesAutoresizingMaskIntoConstraints = false
		return collectionView
	}()
	
	override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .systemBackground
		configure()
    }
	
	func configure() {
		collectionView.dataSource = self
		collectionView.delegate = self
		collectionView.register(BACollectionViewCell.self, forCellWithReuseIdentifier: BACollectionViewCell.cellID)
		
		view.addSubview(collectionView)
		NSLayoutConstraint.activate([
			collectionView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
			collectionView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
			collectionView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
			collectionView.heightAnchor.constraint(equalToConstant: 150)
		])
	}

	func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
		40
	}
	
	func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
		let cell = collectionView.dequeueReusableCell(withReuseIdentifier: BACollectionViewCell.cellID, for: indexPath) as! BACollectionViewCell
		cell.setup(title: "Aidyn", cash: "123 431 T", imageTitle: "airplane.circle.fill")
		return cell
	}

}
